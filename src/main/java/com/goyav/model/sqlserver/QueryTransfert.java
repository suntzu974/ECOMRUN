package com.goyav.model.sqlserver;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QueryTransfert {
	@SerializedName("transfert")
	private List<Transfert> transfert;


	public QueryTransfert() {
	}


	public QueryTransfert(List<Transfert> transfert) {
		this.transfert = transfert;
	}

	public List<Transfert> getTransfert() {
		return this.transfert;
	}

	public void setTransfert(List<Transfert> transfert) {
		this.transfert = transfert;
	}

}
