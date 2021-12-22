package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Tappent {
	@SerializedName("supplier") // dépot target 82
	private short	i1depot;
	@SerializedName("i1natmvt")
	private short	i1natmvt;
	@SerializedName("i4numbl")
	private Integer	i4numbl;
	@SerializedName("emetteur") // depot emetteur 51 - ECOMRUN
	private short	i1depote;
	@SerializedName("i2derlig")
	private short	i2derlig;
	@SerializedName("i1nbmaj")
	private short	i1nbmaj;
	@SerializedName("i1nbedit")
	private short	i1nbedit;
	@SerializedName("vcobs")
	private String	vcobs;
	@SerializedName("vcusrbl")
	private String	vcusrbl;
	@SerializedName("chsolde")
	private String	chsolde;
	@SerializedName("didatcre")
	private long	didatcre;
	@SerializedName("vcusrcre")
	private String	vcusrcre;
	@SerializedName("didatann")
	private long	didatann;
	@SerializedName("chheurann")
	private String	chheurann;
	@SerializedName("vcusrann")
	private String	vcusrann;

	public Tappent() {
	}

	public Tappent(short i1depot, short i1natmvt, Integer i4numbl, short i1depote, short i2derlig, short i1nbmaj, short i1nbedit, 
				String vcobs, String vcusrbl, String chsolde, long didatcre, String vcusrcre, long didatann, String chheurann, String vcusrann) {
		this.i1depot = i1depot;
		this.i1natmvt = i1natmvt;
		this.i4numbl = i4numbl;
		this.i1depote = i1depote;
		this.i2derlig = i2derlig;
		this.i1nbmaj = i1nbmaj;
		this.i1nbedit = i1nbedit;
		this.vcobs = vcobs;
		this.vcusrbl = vcusrbl;
		this.chsolde = chsolde;
		this.didatcre = didatcre;
		this.vcusrcre = vcusrcre;
		this.didatann = didatann;
		this.chheurann = chheurann;
		this.vcusrann = vcusrann;
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

	public short getI1depote() {
		return this.i1depote;
	}

	public void setI1depote(short i1depote) {
		this.i1depote = i1depote;
	}

	public short getI2derlig() {
		return this.i2derlig;
	}

	public void setI2derlig(short i2derlig) {
		this.i2derlig = i2derlig;
	}

	public short getI1nbmaj() {
		return this.i1nbmaj;
	}

	public void setI1nbmaj(short i1nbmaj) {
		this.i1nbmaj = i1nbmaj;
	}

	public short getI1nbedit() {
		return this.i1nbedit;
	}

	public void setI1nbedit(short i1nbedit) {
		this.i1nbedit = i1nbedit;
	}

	public String getVcobs() {
		return this.vcobs;
	}

	public void setVcobs(String vcobs) {
		this.vcobs = vcobs;
	}

	public String getVcusrbl() {
		return this.vcusrbl;
	}

	public void setVcusrbl(String vcusrbl) {
		this.vcusrbl = vcusrbl;
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

	public String getVcusrcre() {
		return this.vcusrcre;
	}

	public void setVcusrcre(String vcusrcre) {
		this.vcusrcre = vcusrcre;
	}

	public long getDidatann() {
		return this.didatann;
	}

	public void setDidatann(long didatann) {
		this.didatann = didatann;
	}

	public String getChheurann() {
		return this.chheurann;
	}

	public void setChheurann(String chheurann) {
		this.chheurann = chheurann;
	}

	public String getVcusrann() {
		return this.vcusrann;
	}

	public void setVcusrann(String vcusrann) {
		this.vcusrann = vcusrann;
	}

}
