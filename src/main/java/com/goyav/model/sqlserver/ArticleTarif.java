package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class ArticleTarif {
	@SerializedName("coderav")
	private String	chcodi;


	public ArticleTarif() {
	}

	public ArticleTarif(String chcodi) {
		this.chcodi = chcodi;
	}

	public String getChcodi() {
		return this.chcodi;
	}

	public void setChcodi(String chcodi) {
		this.chcodi = chcodi;
	}

}
