package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Rdepot {
	@SerializedName("i1depot")
	private short	i1depot;
	@SerializedName("i1groupdep")
	private short	i1groupdep;


	public Rdepot() {
	}


	public Rdepot(short i1depot, short i1groupdep) {
		this.i1depot = i1depot;
		this.i1groupdep = i1groupdep;
	}

	public short getI1depot() {
		return this.i1depot;
	}

	public void setI1depot(short i1depot) {
		this.i1depot = i1depot;
	}

	public short getI1groupdep() {
		return this.i1groupdep;
	}

	public void setI1groupdep(short i1groupdep) {
		this.i1groupdep = i1groupdep;
	}

}
