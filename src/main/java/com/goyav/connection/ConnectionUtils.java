package com.goyav.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.goyav.model.configuration.Server;


public class ConnectionUtils {
	private Server server;

	public ConnectionUtils() {
		super();
	}

	public  Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}

	public Connection getIngresConnection() 
			throws ClassNotFoundException, SQLException {
			return IngresConnUtils.getIngresConnection(getServer().getIngres());
		}
    
	public Connection getSQLServerMicrosoftConnection() 
			throws ClassNotFoundException, SQLException {
	      	return SQLServerMicrosoftConn.getSQLServerMicrosofConnection(getServer().getMicrosoft());
		}	


	public void closeQuietly(Connection conn) {
		try {
            conn.close();
        	} catch (Exception e) {
        }
    }

}