package com.goyav.controller.sofarem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.google.gson.Gson;
import com.goyav.database.DbEcomrun;
import com.goyav.model.configuration.Server;
import com.goyav.model.sqlserver.TstkX3;
import com.goyav.utils.FtpClient;
import com.goyav.utils.SFTPClient;
import com.goyav.utils.Tools;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

/**
 * Servlet implementation class StockEcomrun
 */
@WebServlet("/stockecomrunextended")
public class StockEcomrunExtended extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Tools tools = new Tools();
	private DbEcomrun dbquery = new DbEcomrun();       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockEcomrunExtended() {
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
        String fileName = new String("sofarem-hti.csv");
        String file2download = new String("coderav.txt");
        List<TstkX3> list = null;
        List<String> articles = new ArrayList<String>();
        
   		try {
   			dbquery = new DbEcomrun(server.getMicrosoft());
   			list = dbquery.queryStockEcomrunExtended(conn);
   			
   			
   			if (server.getFtprav().getProtocol().contentEquals("sftp")) {
   	   			SFTPClient sftpClient = new SFTPClient(server.getFtprav());
   	   			sftpClient.connect();
   	   			articles = sftpClient.downloadToList(file2download);;
   	   			sftpClient.disconnect();
   			}
   			if (server.getFtpserver().getProtocol().contentEquals("sftp")) {
   	   			SFTPClient sftpClient = new SFTPClient(server.getFtpserver());
   	   			sftpClient.connect();
   	   			sftpClient.upload(writeListObjects2CsvFile(tools.FilterList(list,articles), fileName), fileName);
   	   			sftpClient.disconnect();
   			}
   			
   		} catch (SQLException | JSchException | SftpException e) {
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
	private String writeListObjects2CsvFile(List<TstkX3> stocks, String pathFile) {
	    final String[] CSV_HEADER = { "i1ent", "i1depot", "chcodi", "codex3","f8qtestk" };
	    
	    FileWriter fileWriter = null;
	    CSVPrinter csvPrinter = null;
	    String contextPath = getServletContext().getRealPath("/");
		String xmlFilePath=contextPath+File.separator+pathFile;
		System.out.println(xmlFilePath);
	    
	    try {
	    	
	    	
	      fileWriter = new FileWriter(xmlFilePath);
	      csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(CSV_HEADER));
	 
	      for (TstkX3 stock : stocks) {
	        List<Object> data = Arrays.asList(
	            stock.getI1ent(),
	            stock.getI1depot(),
	            stock.getChcodi(),
	            stock.getCodex3(),
	            String.valueOf(stock.getF8qtestk()));
	        
	        csvPrinter.printRecord(data);
	      }
	    } catch (Exception e) {
	      System.out.println("Writing CSV error!");
	      e.printStackTrace();
	    } finally {
	      try {
	        fileWriter.flush();
	        fileWriter.close();
	        csvPrinter.close();
	      } catch (IOException e) {
	        System.out.println("Flushing/closing error!");
	        e.printStackTrace();
	      }
	    }
	    return xmlFilePath;
	  }

}
