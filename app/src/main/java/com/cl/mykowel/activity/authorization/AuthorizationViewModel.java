package com.cl.mykowel.activity.authorization;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.cl.mykowel.R;
import com.cl.mykowel.activity.MainActivity;
import com.cl.mykowel.model.model_my_kovel.model_user.User;
import com.cl.mykowel.service.ApiService;
import com.cl.mykowel.service.RetroInstanceMyKowel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthorizationViewModel extends AndroidViewModel {
    //дивиться за активіті і записує все мутебул дані

    private final MutableLiveData<User> createNewUserLiveData;

    public AuthorizationViewModel(@NonNull  Application application) {
        super(application);
        createNewUserLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<User> getCreateUserObserver(){

        return createNewUserLiveData;
    }




    public void createNewUser(User user,Context context) {
        ApiService retroServiceInterface = RetroInstanceMyKowel.getKowelService();


        Call<User> call = retroServiceInterface.postUserLogin(createPartFromString(user.getLogin()), createPartFromString(user.getPassword()));

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //якшо успішно тоді дані із response зберігаються в shared
                // references;

                if(response.isSuccessful()) {
                    // Get the response object
                    User user1 = response.body();

                    // створюється SharedPreferences
                    SharedPreferences sharedPref = context.getSharedPreferences(String.valueOf(R.string.shared_preferences_user_data), Context.MODE_PRIVATE);

                    // Створюється editor
                    SharedPreferences.Editor editor = sharedPref.edit();


                    editor.clear();
                    editor.putBoolean("account_created", true);
                    editor.putString("login", user1.getLogin());
                    editor.putString("name", user1.getName());
                    editor.putString("phone", user1.getPhone());
                    editor.putString("token", user1.getToken());
                    editor.putString("is_admin", user1.getIs_admin());

                    // Apply the changes
                    editor.apply();

                    context.startActivity(new Intent(context, MainActivity.class));

                } else {

                    createNewUserLiveData.postValue( null);
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                createNewUserLiveData.postValue(null);
            }
        });
    }

    private RequestBody createPartFromString(String descriptionString){
        return RequestBody.create(MultipartBody.FORM, descriptionString);
    }


}

