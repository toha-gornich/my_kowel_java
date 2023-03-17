package com.cl.mykowel.fragments.bazar;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.mykowel.model.model_my_kovel.model_bazar.BazarRepository;
import com.cl.mykowel.model.model_my_kovel.model_bazar.ItemBazar;

import java.util.List;

public class BazarViewModel extends AndroidViewModel {

//    private final String token;

    private final BazarRepository bazarRepository;

    private final MutableLiveData<RecyclerView> mBazar;

    public BazarViewModel(Application application) {
        super(application);
        mBazar = new MutableLiveData<>();
//        this.token = token;
        bazarRepository = new BazarRepository();
    }

    public MutableLiveData<List<ItemBazar>> getBazarRepository() {
        return bazarRepository.getMutableLiveData();
    }

    public MutableLiveData<RecyclerView> getmBazar() {
        return mBazar;
    }
}