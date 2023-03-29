package com.cl.mykowel.activity.main.account.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.cl.mykowel.activity.main.account.model.MyItemBazar;
import com.cl.mykowel.model.model_my_kovel.model_bazar.ItemBazar;

@Database(entities = {MyItemBazar.class}, version = 1)
public abstract class ItemsBazarDatabase extends RoomDatabase {

    public abstract ItemBazarDAO getItemBazarDAO();
}
