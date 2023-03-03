package com.cl.mykowel.model.model_news;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemNews {

//items клас який хранить наші карточки які будуть відображатись на головному екрані
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}



