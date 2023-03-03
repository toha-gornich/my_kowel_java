package com.cl.mykowel.model.model_my_kovel.model_contact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ItemContact {

    @SerializedName("pnumber")
    @Expose
    private String pnumber;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}