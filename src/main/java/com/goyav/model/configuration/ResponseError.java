package com.goyav.model.configuration;

import com.google.gson.annotations.SerializedName;

public class ResponseError {

	@SerializedName("code")
	private Integer code;
	@SerializedName("error")
	private String error;
	public ResponseError() {
		// TODO Auto-generated constructor stub
	}
	public ResponseError(Integer code, String error) {
		super();
		this.code = code;
		this.error = error;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
