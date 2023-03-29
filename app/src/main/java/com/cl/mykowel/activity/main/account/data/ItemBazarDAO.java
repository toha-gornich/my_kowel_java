package com.cl.mykowel.activity.main.account.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cl.mykowel.activity.main.account.model.MyItemBazar;
import com.cl.mykowel.model.model_my_kovel.model_bazar.ItemBazar;

import java.util.List;

@Dao
public interface ItemBazarDAO {

    @Insert
    public int addItemBazar(MyItemBazar myItemBazar);

    @Update
    public void updateItemBazar(MyItemBazar myItemBazar);

    @Delete
    public void deleteItemBazar(MyItemBazar myItemBazar);

    @Query("select * from tableItemBazar")
    public List<MyItemBazar> getAllItemBazar();

    @Query("select * from tableItemBazar where my_item_bazar_id ==:itemBazarId")
    public MyItemBazar getItemBazar(int itemBazarId);
}
