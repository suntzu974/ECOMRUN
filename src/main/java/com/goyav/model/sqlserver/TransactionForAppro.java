package com.goyav.model.sqlserver;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TransactionForAppro {
	@SerializedName("header") // dépot target 82
	private Tappent header;
	@SerializedName("body")
	private List<Tapplig> body;

	public TransactionForAppro() {
	}

	public TransactionForAppro(Tappent header, List<Tapplig> body) {
		this.header = header;
		this.body = body;
	}

	public Tappent getHeader() {
		return this.header;
	}

	public void setHeader(Tappent header) {
		this.header = header;
	}

	public List<Tapplig> getBody() {
		return this.body;
	}

	public void setBody(List<Tapplig> body) {
		this.body = body;
	}

}
