package com.goyav.model.sqlserver;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseTarif {
	@SerializedName("tarif")
	private List<Tarif> tarif;

	public ResponseTarif() {
	}

	public ResponseTarif(List<Tarif> tarif) {
		this.tarif = tarif;
	}

	public List<Tarif> getTarif() {
		return this.tarif;
	}

	public void setTarif(List<Tarif> tarif) {
		this.tarif = tarif;
	}

}
