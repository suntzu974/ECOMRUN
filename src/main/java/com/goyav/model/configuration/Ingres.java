package com.goyav.model.configuration;

import com.google.gson.annotations.SerializedName;

public class Ingres {
	@SerializedName("driver")
	private String driver;
	@SerializedName("connector")
	private String connector;
	@SerializedName("supplier")
	private String supplier;
	@SerializedName("host")
	private String host;
	@SerializedName("database")
	private String database;
	@SerializedName("port")
	private String port;
	@SerializedName("user")
	private String user;
	@SerializedName("password")
	private String password;

	public Ingres() {
		// TODO Auto-generated constructor stub
	}

	public Ingres(String connector, String supplier, String host, String database, String port, String user,
			String password) {
		super();
		this.connector = connector;
		this.supplier = supplier;
		this.host = host;
		this.database = database;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
