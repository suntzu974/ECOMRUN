package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Transfert {
	@SerializedName("refcli")
	private String	vcrefcli;
	@SerializedName("coderav")
	private String	chcodi;
	@SerializedName("supplier")
	private short	i1depot;
	@SerializedName("quantity")
	private double	f8qte;

	public Transfert() {
		// TODO Auto-generated constructor stub
	}

	public Transfert(String vcrefcli , String chcodi, short i1depot, double f8qte) {
		super();
		this.vcrefcli = vcrefcli;
		this.chcodi = chcodi;
		this.i1depot = i1depot;
		this.f8qte = f8qte;
	}

	public String getVcrefcli() {
		return this.vcrefcli;
	}

	public void setVcrefcli(String vcrefcli) {
		this.vcrefcli = vcrefcli;
	}

	public String getChcodi() {
		return this.chcodi;
	}

	public void setChcodi(String chcodi) {
		this.chcodi = chcodi;
	}

	public short getI1depot() {
		return this.i1depot;
	}

	public void setI1depot(short i1depot) {
		this.i1depot = i1depot;
	}

	public double getF8qte() {
		return this.f8qte;
	}

	public void setF8qte(double f8qte) {
		this.f8qte = f8qte;
	}


}
