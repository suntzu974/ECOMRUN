package com.goyav.model.configuration;

import com.google.gson.annotations.SerializedName;

public class Server {
	@SerializedName("microsoft")
	private X3v12prod microsoft;
	@SerializedName("ingres")
	private Ingres ingres;
	@SerializedName("port")
	private Integer Port;
	@SerializedName("log")
	private String Log;
	@SerializedName("upload")
	private ftpserver ftpserver;
	@SerializedName("download")
	private ftpserver ftprav;
	
	public Server() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Server(X3v12prod microsoft, Ingres ingres,Integer port, String log, com.goyav.model.configuration.ftpserver ftpserver,
			com.goyav.model.configuration.ftpserver ftprav) {
		super();
		this.microsoft = microsoft;
		this.ingres = ingres;
		Port = port;
		Log = log;
		this.ftpserver = ftpserver;
		this.ftprav = ftprav;
	}

	public X3v12prod getMicrosoft() {
		return microsoft;
	}

	public void setMicrosoft(X3v12prod microsoft) {
		this.microsoft = microsoft;
	}

	public Ingres getIngres() {
		return ingres;
	}

	public void setIngres(Ingres ingres) {
		this.ingres = ingres;
	}


	public Integer getPort() {
		return Port;
	}

	public void setPort(Integer port) {
		Port = port;
	}

	public String getLog() {
		return Log;
	}

	public void setLog(String log) {
		Log = log;
	}

	public ftpserver getFtpserver() {
		return ftpserver;
	}

	public void setFtpserver(ftpserver ftpserver) {
		this.ftpserver = ftpserver;
	}

	public ftpserver getFtprav() {
		return ftprav;
	}

	public void setFtprav(ftpserver ftprav) {
		this.ftprav = ftprav;
	}
	

}
