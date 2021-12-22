package com.goyav.connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.goyav.model.configuration.X3v12prod;

public class SQLServerMicrosoftConn {
	// Connect to SQLServer
    // (Using JDBC Driver of JTDS library)
    public static Connection getSQLServerMicrosofConnection(X3v12prod server) //
            throws SQLException, ClassNotFoundException {
 
        return getSQLServerMicrosoftConnection(server.getDriver(),server.getConnector(),
        								server.getSupplier(),server.getHost(),server.getInstance(),server.getPort(),
        								server.getDatabase(),server.getUser(),server.getPassword());
    }
 
    public static Connection getSQLServerMicrosoftConnection(String Driver,String connector, String supplier,String host, String instance,  String port, String database,String user, String password) throws SQLException,
    ClassNotFoundException {
		 Class.forName(Driver);
		 Connection conn = (Connection) DriverManager.getConnection(connector+":"+supplier+"://"+host+":"+port+";databaseName="+database+";instance="+instance+";user="+user+";password="+password);

		 DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
		          System.out.println("Driver name: " + dm.getDriverName());
		          System.out.println("Driver version: " + dm.getDriverVersion());
		          System.out.println("Product name: " + dm.getDatabaseProductName());
		          System.out.println("Product version: " + dm.getDatabaseProductVersion());
		 return conn;
	}
}
