package com.goyav.model.sqlserver;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QueryStock {
	@SerializedName("stockingres")
	private List<Stock> stock;


	public QueryStock() {
	}

	public QueryStock(List<Stock> stock) {
		this.stock = stock;
	}

	public List<Stock> getStock() {
		return this.stock;
	}

	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}

}
