package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Stock {
	@SerializedName("supplier")
	private short	i1depot;
	@SerializedName("coderav")
	private String	chcodi;
	@SerializedName("quantity")
	private double	f8qtestk;

	public Stock() {
		// TODO Auto-generated constructor stub
	}

	public Stock(short i1depot,String chcodi, double f8qtestk) {
		super();
		this.chcodi = chcodi;
		this.i1depot = i1depot;
		this.f8qtestk = f8qtestk;
	}

	public String getChcodi() {
		return chcodi;
	}

	public void setChcodi(String chcodi) {
		this.chcodi = chcodi;
	}

	public short getI1depot() {
		return i1depot;
	}

	public void setI1depot(short i1depot) {
		this.i1depot = i1depot;
	}

	public double getF8qtestk() {
		return f8qtestk;
	}

	public void setF8qtestk(double f8qtestk) {
		this.f8qtestk = f8qtestk;
	}


}
