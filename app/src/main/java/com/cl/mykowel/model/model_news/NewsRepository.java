package com.cl.mykowel.model.model_news;


import androidx.lifecycle.MutableLiveData;

import com.cl.mykowel.service.ApiService;
import com.cl.mykowel.service.RetroInstanceKowelMedia;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//проводиться сам сапит і відсилається у мутабил типі в клас ввм
public class NewsRepository {

    private ArrayList<ItemNews> itemNews = new ArrayList<>();
    private MutableLiveData<List<ItemNews>> mutableLiveData =
            new MutableLiveData<>();
//    private Application application;
//
//
//
//
//    public NewsRepository(Application application) {
//        this.application = application;
//    }




    public MutableLiveData<List<ItemNews>> getMutableLiveData() {
    //викликає NewsApiService а саме retrofit
        ApiService apiService = RetroInstanceKowelMedia.getNewsService();
        // створюється масив include retrofit
        Call<NewsApiResponse> call = apiService
                .getItemsNews();
        //формуються обєкти класів з якими ми в подальшо праюємо типу переміщуєм з retrofit in our object
        //а також проводим його провірку на те чи retrofit not null томи його заповнюєм
        call.enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call,
                                   Response<NewsApiResponse> response) {

                NewsApiResponse newsApiResponse =
                        response.body();

                if (newsApiResponse != null &&
                        newsApiResponse.getItems() != null) {

                    itemNews = (ArrayList<ItemNews>) newsApiResponse
                            .getItems();
                    mutableLiveData.setValue(itemNews);

                }

            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

            }
        });
//повертаєм дані в HomeViewModel
        return mutableLiveData;
    }
}
