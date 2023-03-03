package com.cl.mykowel.model.model_news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiResponse{
    //тут міститься масив наших Items які ми будемо показувати на головному екрані як новини
    @SerializedName("items")
    @Expose
    private List<ItemNews> items = null;

    public List<ItemNews> getItems() {
        return items;
    }

    public void setItems(List<ItemNews> items) {
        this.items = items;
    }
}