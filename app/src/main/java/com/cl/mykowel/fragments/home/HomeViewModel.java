package com.cl.mykowel.fragments.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.mykowel.model.model_news.ItemNews;
import com.cl.mykowel.model.model_news.NewsRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    //  newsRepository клас в якому методи роблють запит на сервер і отримують

    private NewsRepository newsRepository;

    // MutableLiveData спостерігає і може бути бути зміненим від спостерігає,

    private final MutableLiveData<RecyclerView> mNews;

// призначений для даних які надїодять із fragment_news_list
    public HomeViewModel(@NonNull Application application) {
        super(application);
        mNews = new MutableLiveData<>();

        newsRepository = new NewsRepository();
    }

//щоб отримати для нашого NewsFragment з NewsRepository
    //викликає
    public LiveData<List<ItemNews>> getAllNewsData() {
        return newsRepository.getMutableLiveData();
    }

//щоб отримати  для нашого NewsFragment with NewsRecyclerViewAdapter
    public LiveData<RecyclerView> getNews() {
        return mNews;
    }
}