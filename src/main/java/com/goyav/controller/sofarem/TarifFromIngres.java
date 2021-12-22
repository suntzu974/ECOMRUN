package com.goyav.controller.sofarem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.goyav.database.DbEcomrun;
import com.goyav.model.configuration.Server;
import com.goyav.model.sqlserver.Stock;
import com.goyav.model.sqlserver.QueryTarif;
import com.goyav.model.sqlserver.ResponseStock;
import com.goyav.model.sqlserver.ResponseTarif;
import com.goyav.utils.SFTPClient;
import com.goyav.utils.Tools;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class StockEcomrun
 */
@WebServlet("/tarif")
public class TarifFromIngres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Tools tools = new Tools();
	private DbEcomrun dbquery = new DbEcomrun();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TarifFromIngres() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = tools.getStoredConnectionMicrosoft(request);
		Server server = tools.getStoredServer(request);
		Connection ingres = tools.getStoredConnectionIngres(request);
		Gson gson  = new GsonBuilder().create();
		QueryTarif query = new QueryTarif();
		ResponseTarif list = null;
		query = gson.fromJson(tools.decodeRequest(request), QueryTarif.class);
		System.out.println(request);
		System.out.println(query);
   		try {
   			list = dbquery.queryTarifFromIngres(ingres,query);
   		} catch (SQLException e) {
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
	
}
