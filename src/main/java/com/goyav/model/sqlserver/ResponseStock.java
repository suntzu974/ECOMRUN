package com.goyav.model.sqlserver;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseStock {
	@SerializedName("stockingres")
	private List<Stock> stock;

	public ResponseStock() {
	}

	public ResponseStock(List<Stock> stock) {
		this.stock = stock;
	}

	public List<Stock> getStock() {
		return this.stock;
	}

	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}

}
