package com.cl.mykowel.fragments.events;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.mykowel.model.model_my_kovel.model_event.EventRepository;
import com.cl.mykowel.model.model_my_kovel.model_event.ItemEvent;

import java.util.List;

public class EventsViewModel extends AndroidViewModel {

    private final MutableLiveData<RecyclerView> mEvent;

    private EventRepository eventRepository;

    public  EventsViewModel(Application application){
        super(application);
        mEvent = new MutableLiveData<>();
        eventRepository = new EventRepository();

    }

    public MutableLiveData<List<ItemEvent>> getEventRepository() {
        return eventRepository.getMutableLiveData();
    }

    public MutableLiveData<RecyclerView> getRecyclerViewMutableLiveData() {
        return mEvent;
    }
}