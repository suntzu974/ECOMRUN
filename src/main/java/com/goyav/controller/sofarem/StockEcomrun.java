package com.goyav.controller.sofarem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.goyav.database.DbEcomrun;
import com.goyav.model.configuration.Server;
import com.goyav.model.sqlserver.Tstk;
import com.goyav.utils.SFTPClient;
import com.goyav.utils.Tools;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

/**
 * Servlet implementation class StockEcomrun
 */
@WebServlet("/stockecomrun")
public class StockEcomrun extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Tools tools = new Tools();
	private DbEcomrun dbquery = new DbEcomrun();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockEcomrun() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = tools.getStoredConnectionMicrosoft(request);
		 Server server = tools.getStoredServer(request);
		Connection ingres = tools.getStoredConnectionIngres(request);
        Gson gson = new Gson();
        String file2download = new String("coderav.txt");
        List<Tstk> list = null;
        List<String> articles = new ArrayList<String>();
   		try {
   			dbquery = new DbEcomrun(server.getMicrosoft());
   			list = dbquery.queryStockEcomrun(conn);
   			if (server.getFtprav().getProtocol().contentEquals("sftp")) {
   	   			SFTPClient sftpClient = new SFTPClient(server.getFtprav());
   	   			sftpClient.connect();
   	   			articles = sftpClient.downloadToList(file2download);;
   	   			sftpClient.disconnect();
   			}
   			/* Insertion dans TSTK */
   			dbquery.deleteTstk(ingres);
   			dbquery.insertTstk(ingres, tools.FilterEcomrun(list,articles));
   		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
			ingres.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getOutputStream().print(gson.toJson(list));
        response.getOutputStream().flush();

	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection ingres = tools.getStoredConnectionIngres(request);
   		try {
   			dbquery.deleteTstk(ingres);
   		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
