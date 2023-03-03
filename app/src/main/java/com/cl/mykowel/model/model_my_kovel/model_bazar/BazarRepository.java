package com.cl.mykowel.model.model_my_kovel.model_bazar;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.cl.mykowel.service.ApiService;
import com.cl.mykowel.service.RetroInstanceMyKowel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BazarRepository {

    private final ArrayList<ItemBazar> itemBazars = new ArrayList<>();
    private final MutableLiveData<List<ItemBazar>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<ItemBazar>> getMutableLiveData(){

        ApiService apiService = RetroInstanceMyKowel.getKowelService();

        Call<List<ItemBazar>> call = apiService.getItemBazar();

        call.enqueue(new Callback<List<ItemBazar>>() {
            @Override
            public void onResponse( Call<List<ItemBazar>> call, Response<List<ItemBazar>> response) {
                if(response.isSuccessful()){
                    List<ItemBazar> bazars = response.body();

                    mutableLiveData.setValue(bazars);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ItemBazar>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
