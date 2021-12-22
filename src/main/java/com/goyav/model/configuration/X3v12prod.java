package com.goyav.model.configuration;

import com.google.gson.annotations.SerializedName;

public class X3v12prod {
	@SerializedName("driver")
	private String driver;
	@SerializedName("connector")
	private String connector;
	@SerializedName("supplier")
	private String supplier;
	@SerializedName("instance")
	private String instance;
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
	@SerializedName("schema")
	private String schema;
	public X3v12prod() {
		super();
		// TODO Auto-generated constructor stub
	}
	public X3v12prod(String driver, String connector, String supplier, String instance, String host, String database,
			String port, String user, String password, String schema) {
		super();
		this.driver = driver;
		this.connector = connector;
		this.supplier = supplier;
		this.instance = instance;
		this.host = host;
		this.database = database;
		this.port = port;
		this.user = user;
		this.password = password;
		this.schema = schema;
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
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
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
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}


}
