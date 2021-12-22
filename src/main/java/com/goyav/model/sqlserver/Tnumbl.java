package com.goyav.model.sqlserver;

import com.google.gson.annotations.SerializedName;

public class Tnumbl {
    @SerializedName("i1ent")
    private short i1ent;
    @SerializedName("i1depot")
    private short i1depot;
    @SerializedName("i1natmvt")
    private short i1natmvt;
    @SerializedName("i4numbl")
    private Integer i4numbl;
    @SerializedName("cfil1024")
    private String cfil1014;

    public Tnumbl() {
    }


    public Tnumbl(short i1ent, short i1depot, short i1natmvt, Integer i4numbl, String cfil1014) {
        this.i1ent = i1ent;
        this.i1depot = i1depot;
        this.i1natmvt = i1natmvt;
        this.i4numbl = i4numbl;
        this.cfil1014 = cfil1014;
    }

    public short getI1ent() {
        return this.i1ent;
    }

    public void setI1ent(short i1ent) {
        this.i1ent = i1ent;
    }

    public short getI1depot() {
        return this.i1depot;
    }

    public void setI1depot(short i1depot) {
        this.i1depot = i1depot;
    }

    public short getI1natmvt() {
        return this.i1natmvt;
    }

    public void setI1natmvt(short i1natmvt) {
        this.i1natmvt = i1natmvt;
    }

    public Integer getI4numbl() {
        return this.i4numbl;
    }

    public void setI4numbl(Integer i4numbl) {
        this.i4numbl = i4numbl;
    }

    public String getCfil1014() {
        return this.cfil1014;
    }

    public void setCfil1014(String cfil1014) {
        this.cfil1014 = cfil1014;
    }
    
}
