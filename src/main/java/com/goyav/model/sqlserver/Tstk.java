package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Tstk {
	@SerializedName("i1ent")
	private short	i1ent;
	@SerializedName("chcodi")
	private String	chcodi;
	@SerializedName("i1depot")
	private short	i1depot;
	@SerializedName("f8qtestk")
	private double	f8qtestk;
	@SerializedName("f8qteres")
	private transient double	f8qteres;
	@SerializedName("f8qtenl")
	private transient double	f8qtenl;
	@SerializedName("f8stkarlert")
	private transient double	f8stkalert;
	@SerializedName("didatrupt")
	private transient long	didatrupt;
	@SerializedName("didateler")
	private transient long	didatalert;
	@SerializedName("didatdent")
	private transient long	didatdent;
	@SerializedName("didatdsor")
	private transient long	didatdsor;
	@SerializedName("didatdinv")
	private transient long	didatdinv;
	@SerializedName("vcempl")
	private transient String	vcempl;

	public Tstk() {
		// TODO Auto-generated constructor stub
	}

	public Tstk(short i1ent, String chcodi, short i1depot, double f8qtestk, double f8qteres, double f8qtenl,
			double f8stkalert, long didatrupt, long didatalert, long didatdent, long didatdsor, long didatdinv,
			String vcempl) {
		super();
		this.i1ent = i1ent;
		this.chcodi = chcodi;
		this.i1depot = i1depot;
		this.f8qtestk = f8qtestk;
		this.f8qteres = f8qteres;
		this.f8qtenl = f8qtenl;
		this.f8stkalert = f8stkalert;
		this.didatrupt = didatrupt;
		this.didatalert = didatalert;
		this.didatdent = didatdent;
		this.didatdsor = didatdsor;
		this.didatdinv = didatdinv;
		this.vcempl = vcempl;
	}

	public short getI1ent() {
		return i1ent;
	}

	public void setI1ent(short i1ent) {
		this.i1ent = i1ent;
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

	public double getF8qteres() {
		return f8qteres;
	}

	public void setF8qteres(double f8qteres) {
		this.f8qteres = f8qteres;
	}

	public double getF8qtenl() {
		return f8qtenl;
	}

	public void setF8qtenl(double f8qtenl) {
		this.f8qtenl = f8qtenl;
	}

	public double getF8stkalert() {
		return f8stkalert;
	}

	public void setF8stkalert(double f8stkalert) {
		this.f8stkalert = f8stkalert;
	}

	public long getDidatrupt() {
		return didatrupt;
	}

	public void setDidatrupt(long didatrupt) {
		this.didatrupt = didatrupt;
	}

	public long getDidatalert() {
		return didatalert;
	}

	public void setDidatalert(long didatalert) {
		this.didatalert = didatalert;
	}

	public long getDidatdent() {
		return didatdent;
	}

	public void setDidatdent(long didatdent) {
		this.didatdent = didatdent;
	}

	public long getDidatdsor() {
		return didatdsor;
	}

	public void setDidatdsor(long didatdsor) {
		this.didatdsor = didatdsor;
	}

	public long getDidatdinv() {
		return didatdinv;
	}

	public void setDidatdinv(long didatdinv) {
		this.didatdinv = didatdinv;
	}

	public String getVcempl() {
		return vcempl;
	}

	public void setVcempl(String vcempl) {
		this.vcempl = vcempl;
	}

}
