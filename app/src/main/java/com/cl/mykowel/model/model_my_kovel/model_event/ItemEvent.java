package com.cl.mykowel.model.model_my_kovel.model_event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemEvent {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("id")
    @Expose
    private Integer id;

    public ItemEvent(String title, String place, String date, Integer id) {
        this.title = title;
        this.place = place;
        this.date = date;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
