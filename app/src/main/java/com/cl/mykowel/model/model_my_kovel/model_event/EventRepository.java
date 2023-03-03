package com.cl.mykowel.model.model_my_kovel.model_event;

import androidx.lifecycle.MutableLiveData;

import com.cl.mykowel.service.ApiService;
import com.cl.mykowel.service.RetroInstanceMyKowel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private MutableLiveData<List<ItemEvent>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<ItemEvent>itemEvents =  new ArrayList<>();

    public MutableLiveData<List<ItemEvent>> getMutableLiveData(){
        ApiService apiService = RetroInstanceMyKowel.getKowelService();
        Call<List<ItemEvent>> call = apiService.getItemEvent();
        call.enqueue(new Callback<List<ItemEvent>>() {
            @Override
            public void onResponse(Call<List<ItemEvent>> call, Response<List<ItemEvent>> response) {
                if (response.isSuccessful()) {
                    List<ItemEvent> events = response.body();
                    mutableLiveData.setValue(events);
                    // записуємо дані у MutableLiveData
                } else {
                    // обробка невдалої відповіді
                }
            }

            @Override
            public void onFailure(Call<List<ItemEvent>> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }
}
