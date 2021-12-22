package com.goyav.model.configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.goyav.connection.ConnectionUtils;
import com.goyav.utils.Tools;


/**
 * Servlet Filter implementation class JDBCFilter
 */
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {
	private Server server = new Server();
	private  ConnectionUtils connection = new ConnectionUtils();
	private Tools tools = new Tools();
    public JDBCFilter() {
        // TODO Auto-generated constructor stub
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("ecomrun.json")) {
        	BufferedReader bR = new BufferedReader(  new InputStreamReader(inputStream));
        	String line = "";

        	StringBuilder responseStrBuilder = new StringBuilder();
        	while((line =  bR.readLine()) != null){

        	    responseStrBuilder.append(line);
        	}
        	inputStream.close();
        	 JsonElement elem = new JsonParser().parse(responseStrBuilder.toString());
        	    Gson gson  = new GsonBuilder().create();
        	   server  = gson.fromJson(elem, Server.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setServer(server);
    }

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
	
    private boolean needJDBC(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String urlPattern = servletPath;
        if (pathInfo != null) {
            urlPattern = servletPath + "/*";
        }
 
        
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
                .getServletRegistrations();
        Collection<? extends ServletRegistration> values = servletRegistrations.values();
        for (ServletRegistration sr : values) {
            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        if (this.needJDBC(req)) {
 
        	Connection ingres = null;
            Connection x3v12prod = null;
            try {
                // Create a Connection.
            	 ingres = connection.getIngresConnection();
                x3v12prod = connection.getSQLServerMicrosoftConnection();	
                x3v12prod.setAutoCommit(true);
                // Store Connection object in attribute of request.
                tools.storeServer(request,server);
                tools.storeConnectionMicrosoft(request, x3v12prod);
                tools.storeConnectionIngres(request, ingres);
                chain.doFilter(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException();
            } finally {
                connection.closeQuietly(x3v12prod);
                connection.closeQuietly(ingres);
            }
        }
        else {
        	  chain.doFilter(request, response);
        }
    }
}
