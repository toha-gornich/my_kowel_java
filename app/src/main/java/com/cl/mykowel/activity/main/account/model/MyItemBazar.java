package com.cl.mykowel.activity.main.account.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tableItemBazar")

public class MyItemBazar {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "my_item_bazar_id")
    private  int my_id;
    @ColumnInfo(name = "item_bazar_id")
    private  int id;
    @ColumnInfo(name = "item_bazar_title")
    private String title;


    public MyItemBazar(int my_id, int id, String title) {
        this.my_id = my_id;
        this.id = id;
        this.title = title;
    }

    public MyItemBazar(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public MyItemBazar() {
    }

    public int getMy_id() {
        return my_id;
    }

    public void setMy_id(int my_id) {
        this.my_id = my_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
