package com.cl.mykowel.model.model_my_kovel.model_contact;

import androidx.lifecycle.MutableLiveData;

import com.cl.mykowel.service.ApiService;
import com.cl.mykowel.service.RetroInstanceMyKowel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRepository {

    private ArrayList<ItemContact> itemContacts = new ArrayList<>();
    private MutableLiveData<List<ItemContact>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<ItemContact>> getMutableLiveData(){
        ApiService apiService = RetroInstanceMyKowel.getKowelService();
        Call<List<ItemContact>> call = apiService.getItemContact();

        call.enqueue(new Callback<List<ItemContact>>() {
            @Override
            public void onResponse(Call<List<ItemContact>> call, Response<List<ItemContact>> response) {


                if (response.isSuccessful()) {
                    List<ItemContact> contacts = response.body();
                    mutableLiveData.setValue(contacts);
                    // записуємо дані у MutableLiveData
                } else {
                    // обробка невдалої відповіді
                }
//                ContactsApiResponse contactsApiResponse = response.body();
//                if(contactsApiResponse != null && contactsApiResponse.getItems() != null){
//                    itemContacts = (ArrayList<ItemContact>) contactsApiResponse.getItems();
//                    mutableLiveData.setValue(itemContacts);
//                }

//                if( contacts != null){
//                    ContactsApiResponse contactsApiResponse = new ContactsApiResponse(contacts);
//
//                    if(contactsApiResponse != null && contactsApiResponse.getItems() != null){
//                    itemContacts = (ArrayList<ItemContact>) contactsApiResponse.getItems();
//                    mutableLiveData.setValue(itemContacts);
//                }
            }

            @Override
            public void onFailure(Call<List<ItemContact>> call, Throwable t) {

            }
        });

        return mutableLiveData;
    }


}
