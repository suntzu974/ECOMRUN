package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Article {
	@SerializedName("coderav")
	private String	chcodi;
	@SerializedName("ean")
	private String	chgencod;
	@SerializedName("unitstk")
	private String	chunitstk;

	public Article() {
	}

	public Article(String chcodi, String chgencod, String chunitstk) {
		this.chcodi = chcodi;
		this.chgencod = chgencod;
		this.chunitstk = chunitstk;
	}

	public String getChcodi() {
		return this.chcodi;
	}

	public void setChcodi(String chcodi) {
		this.chcodi = chcodi;
	}

	public String getChgencod() {
		return this.chgencod;
	}

	public void setChgencod(String chgencod) {
		this.chgencod = chgencod;
	}

	public String getChunitstk() {
		return this.chunitstk;
	}

	public void setChunitstk(String chunitstk) {
		this.chunitstk = chunitstk;
	}

}
