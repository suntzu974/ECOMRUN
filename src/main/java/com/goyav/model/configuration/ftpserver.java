package com.goyav.model.configuration;

import com.google.gson.annotations.SerializedName;

public class ftpserver {
	@SerializedName("host")
	private String host;
	@SerializedName("protocol")
	private String protocol;
	@SerializedName("port")
	private String port;
	@SerializedName("user")
	private String user;
	@SerializedName("password")
	private String password;
	@SerializedName("remotedirectory")
	private String remotedirectory;

	public ftpserver() {
		// TODO Auto-generated constructor stub
	}

	public ftpserver(String host, String protocol, String port, String user, String password, String remotedirectory) {
		super();
		this.host = host;
		this.protocol = protocol;
		this.port = port;
		this.user = user;
		this.password = password;
		this.remotedirectory = remotedirectory;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
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

	public String getRemotedirectory() {
		return remotedirectory;
	}

	public void setRemotedirectory(String remotedirectory) {
		this.remotedirectory = remotedirectory;
	}

	
}
