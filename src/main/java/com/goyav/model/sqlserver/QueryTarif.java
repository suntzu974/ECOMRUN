package com.goyav.model.sqlserver;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QueryTarif {
	@SerializedName("tarif")
	private List<ArticleTarif> tarif;


	public QueryTarif() {
	}


	public QueryTarif(List<ArticleTarif> tarif) {
		this.tarif = tarif;
	}

	public List<ArticleTarif> getTarif() {
		return this.tarif;
	}

	public void setTarif(List<ArticleTarif> tarif) {
		this.tarif = tarif;
	}

}
