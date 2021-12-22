package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Target {
	@SerializedName("sens")
	private short sens;
	@SerializedName("emitter")
	private short	emitter;
	@SerializedName("receiver")
	private short	receiver;
	@SerializedName("products")
	QueryTransfert products;

	public Target() {
	}

	public Target(short sens, short emitter, short receiver, QueryTransfert products) {
		this.sens = sens;
		this.emitter = emitter;
		this.receiver = receiver;
		this.products = products;
	}

	public short getSens() {
		return this.sens;
	}

	public void setSens(short sens) {
		this.sens = sens;
	}

	public short getEmitter() {
		return this.emitter;
	}

	public void setEmitter(short emitter) {
		this.emitter = emitter;
	}

	public short getReceiver() {
		return this.receiver;
	}

	public void setReceiver(short receiver) {
		this.receiver = receiver;
	}

	public QueryTransfert getProducts() {
		return this.products;
	}

	public void setProducts(QueryTransfert products) {
		this.products = products;
	}

}
