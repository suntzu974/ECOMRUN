package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Tarif {
	@SerializedName("coderav")
	private String	chcodi;
	@SerializedName("public")
	private double mfpvttc;
	@SerializedName("promo")
	private double mfpromo;
	@SerializedName("debut")
	private Integer debut;
	@SerializedName("fin")
	private Integer fin;
	@SerializedName("tva")
	private double tva;

	public Tarif() {
	}


	public Tarif(String chcodi, double mfpvttc, double mfpromo, Integer debut, Integer fin, double tva) {
		this.chcodi = chcodi;
		this.mfpvttc = mfpvttc;
		this.mfpromo = mfpromo;
		this.debut = debut;
		this.fin = fin;
		this.tva = tva;
	}

	public String getChcodi() {
		return this.chcodi;
	}

	public void setChcodi(String chcodi) {
		this.chcodi = chcodi;
	}

	public double getMfpvttc() {
		return this.mfpvttc;
	}

	public void setMfpvttc(double mfpvttc) {
		this.mfpvttc = mfpvttc;
	}

	public double getMfpromo() {
		return this.mfpromo;
	}

	public void setMfpromo(double mfpromo) {
		this.mfpromo = mfpromo;
	}

	public Integer getDebut() {
		return this.debut;
	}

	public void setDebut(Integer debut) {
		this.debut = debut;
	}

	public Integer getFin() {
		return this.fin;
	}

	public void setFin(Integer fin) {
		this.fin = fin;
	}

	public double getTva() {
		return this.tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

}
