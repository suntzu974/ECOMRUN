package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Tapplig {
	@SerializedName("supplier") // dépot target 82
	private short	i1depot;
	@SerializedName("i1natmvt")
	private short	i1natmvt;
	@SerializedName("i4numbl")
	private Integer	i4numbl;
	@SerializedName("i2numlig") // depot emetteur 51 - ECOMRUN
	private short	i2numlig;
	@SerializedName("chcodi")
	private String	chcodi;
	@SerializedName("f8qte")
	private double	f8qte;
	@SerializedName("chunite")
	private String	chunite;
	@SerializedName("f8qteustk")
	private double	f8qteustk;
	@SerializedName("chunitstk")
	private String	chunitstk;
	@SerializedName("f8qteustkdd")
	private double	f8qteustkdd;
	@SerializedName("chsolde")
	private String	chsolde;
	@SerializedName("didatcre")
	private long	didatcre;
	@SerializedName("chheurcre")
	private String	chheurcre;
	@SerializedName("vcusrcre")
	private String	vcusrcre;
	@SerializedName("didatmaj")
	private long	didatmaj;
	@SerializedName("vcusrmaj")
	private String	vcusrmaj;

	public Tapplig() {
	}


	public Tapplig(short i1depot, short i1natmvt, Integer i4numbl, short i2numlig, String chcodi, double f8qte, String chunite, double f8qteustk, String chunitstk, double f8qteustkdd, String chsolde, long didatcre, String chheurcre, String vcusrcre, long didatmaj, String vcusrmaj) {
		this.i1depot = i1depot;
		this.i1natmvt = i1natmvt;
		this.i4numbl = i4numbl;
		this.i2numlig = i2numlig;
		this.chcodi = chcodi;
		this.f8qte = f8qte;
		this.chunite = chunite;
		this.f8qteustk = f8qteustk;
		this.chunitstk = chunitstk;
		this.f8qteustkdd = f8qteustkdd;
		this.chsolde = chsolde;
		this.didatcre = didatcre;
		this.chheurcre = chheurcre;
		this.vcusrcre = vcusrcre;
		this.didatmaj = didatmaj;
		this.vcusrmaj = vcusrmaj;
	}

	public short getI1depot() {
		return this.i1depot;
	}

	public void setI1depot(short i1depot) {
		this.i1depot = i1depot;
	}

	public short getI1natmvt() {
		return this.i1natmvt;
	}

	public void setI1natmvt(short i1natmvt) {
		this.i1natmvt = i1natmvt;
	}

	public Integer getI4numbl() {
		return this.i4numbl;
	}

	public void setI4numbl(Integer i4numbl) {
		this.i4numbl = i4numbl;
	}

	public short getI2numlig() {
		return this.i2numlig;
	}

	public void setI2numlig(short i2numlig) {
		this.i2numlig = i2numlig;
	}

	public String getChcodi() {
		return this.chcodi;
	}

	public void setChcodi(String chcodi) {
		this.chcodi = chcodi;
	}

	public double getF8qte() {
		return this.f8qte;
	}

	public void setF8qte(double f8qte) {
		this.f8qte = f8qte;
	}

	public String getChunite() {
		return this.chunite;
	}

	public void setChunite(String chunite) {
		this.chunite = chunite;
	}

	public double getF8qteustk() {
		return this.f8qteustk;
	}

	public void setF8qteustk(double f8qteustk) {
		this.f8qteustk = f8qteustk;
	}

	public String getChunitstk() {
		return this.chunitstk;
	}

	public void setChunitstk(String chunitstk) {
		this.chunitstk = chunitstk;
	}

	public double getF8qteustkdd() {
		return this.f8qteustkdd;
	}

	public void setF8qteustkdd(double f8qteustkdd) {
		this.f8qteustkdd = f8qteustkdd;
	}

	public String getChsolde() {
		return this.chsolde;
	}

	public void setChsolde(String chsolde) {
		this.chsolde = chsolde;
	}

	public long getDidatcre() {
		return this.didatcre;
	}

	public void setDidatcre(long didatcre) {
		this.didatcre = didatcre;
	}

	public String getChheurcre() {
		return this.chheurcre;
	}

	public void setChheurcre(String chheurcre) {
		this.chheurcre = chheurcre;
	}

	public String getVcusrcre() {
		return this.vcusrcre;
	}

	public void setVcusrcre(String vcusrcre) {
		this.vcusrcre = vcusrcre;
	}

	public long getDidatmaj() {
		return this.didatmaj;
	}

	public void setDidatmaj(long didatmaj) {
		this.didatmaj = didatmaj;
	}

	public String getVcusrmaj() {
		return this.vcusrmaj;
	}

	public void setVcusrmaj(String vcusrmaj) {
		this.vcusrmaj = vcusrmaj;
	}

}
