package com.cl.mykowel.fragments.contact;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.mykowel.model.model_my_kovel.model_contact.ContactRepository;
import com.cl.mykowel.model.model_my_kovel.model_contact.ItemContact;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private final ContactRepository contactRepository;
    private final MutableLiveData<RecyclerView> mContact;
//    @NonNull Application application
    public ContactViewModel(Application application){
        super(application);
        mContact = new MutableLiveData<>();

        contactRepository = new ContactRepository();
    }

    public MutableLiveData<RecyclerView> getmContact() {
        return mContact;
    }


    public LiveData<List<ItemContact>> getContactRepository() {
        return contactRepository.getMutableLiveData();
    }
}