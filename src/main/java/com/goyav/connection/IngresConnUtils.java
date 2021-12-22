package com.goyav.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.goyav.model.configuration.Ingres;


public class IngresConnUtils {
	  
	 public static Connection getIngresConnection(Ingres ingres)
	         throws ClassNotFoundException, SQLException {
		return getIngresConnection(ingres.getDriver(),ingres.getConnector(),ingres.getSupplier(),ingres.getHost(),ingres.getPort(),ingres.getDatabase(),ingres.getUser(),ingres.getPassword());
	 }

	 public static Connection getIngresConnection(String Driver,String connector, String supplier,String host,  String port, String database,String user, String password) throws SQLException,
     ClassNotFoundException {
		 Class.forName(Driver);
		 Connection conn = (Connection) DriverManager.getConnection(connector+":"+supplier+"://"+host+":"+port+"/"+database,user,password);
		 return conn;
	 }
}
