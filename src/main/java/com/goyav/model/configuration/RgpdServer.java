package com.goyav.model.configuration;

import com.google.gson.annotations.SerializedName;

public class RgpdServer {

	@SerializedName("name")
	private String Name;
	@SerializedName("scheme")
	private String Scheme;
	@SerializedName("host")
	private String Host;
	@SerializedName("port")
	private String Port;
	public RgpdServer() {
		// TODO Auto-generated constructor stub
	}
	public RgpdServer(String name, String scheme, String host, String port) {
		super();
		Name = name;
		Scheme = scheme;
		Host = host;
		Port = port;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getScheme() {
		return Scheme;
	}
	public void setScheme(String scheme) {
		Scheme = scheme;
	}
	public String getHost() {
		return Host;
	}
	public void setHost(String host) {
		Host = host;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}

}
