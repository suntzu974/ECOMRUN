package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Appro {
	@SerializedName("message")
	private String message;
	@SerializedName("appro")
	private Integer	appro;
	@SerializedName("type")
	private short	type;

	public Appro() {
	}

	public Appro(String message, Integer appro, short type) {
		this.message = message;
		this.appro = appro;
		this.type = type;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getAppro() {
		return this.appro;
	}

	public void setAppro(Integer appro) {
		this.appro = appro;
	}

	public short getType() {
		return this.type;
	}

	public void setType(short type) {
		this.type = type;
	}

}
