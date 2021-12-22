package com.goyav.model.sqlserver;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResponseTransfert {
	@SerializedName("response")
	private List<Appro> appro ;

	public ResponseTransfert() {
	}

	public ResponseTransfert(List<Appro> appro) {
		this.appro = appro;
	}

	public List<Appro> getAppro() {
		return this.appro;
	}

	public void setAppro(List<Appro> appro) {
		this.appro = appro;
	}
}
