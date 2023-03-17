package com.cl.mykowel.fragments.bazar.additembazar;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.cl.mykowel.R;
import com.cl.mykowel.model.model_my_kovel.model_bazar.ItemBazar;
import com.cl.mykowel.model.model_my_kovel.model_user.User;
import com.cl.mykowel.service.ApiService;
import com.cl.mykowel.service.RetroInstanceMyKowel;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddItemBazarViewModel extends AndroidViewModel {

    private final MutableLiveData<ItemBazar> itemBazarMutableLiveData;


    public AddItemBazarViewModel(@NonNull Application application) {
        super(application);
        itemBazarMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ItemBazar> getCreateItemBazarObserver(){

        return itemBazarMutableLiveData;
    }


    public  void createItemBazar(ItemBazar itemBazar, Context context){
        ApiService retroServiceInterface = RetroInstanceMyKowel.getKowelService();
        File file = new File(itemBazar.getPhoto());
        SharedPreferences sharedPref = context.getSharedPreferences(String.valueOf(R.string.shared_preferences_user_data), Context.MODE_PRIVATE);
        String token = sharedPref.getString("token","token");

                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));



//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

        Call<ItemBazar> call = retroServiceInterface.postBazarAddItem(token, createPartFromString(itemBazar.getTitle()), createPartFromString(itemBazar.getDescription()),createPartFromString(itemBazar.getPrice()),createPartFromString("0179"),filePart);
//        createPartFromString(token),
        call.enqueue(new Callback<ItemBazar>() {
            @Override
            public void onResponse(Call<ItemBazar> call, Response<ItemBazar> response) {

            }

            @Override
            public void onFailure(Call<ItemBazar> call, Throwable t) {

            }
        });

    }
    private RequestBody createPartFromString(String descriptionString){
        return RequestBody.create(MultipartBody.FORM, descriptionString);
    }
    private  RequestBody createPartFromDouble(Double descriptionString){
        return  RequestBody.create(MultipartBody.FORM, String.valueOf(descriptionString));
    }

}
