package com.cl.mykowel.service;

import com.cl.mykowel.model.model_my_kovel.model_bazar.ItemBazar;
import com.cl.mykowel.model.model_my_kovel.model_contact.ItemContact;
import com.cl.mykowel.model.model_my_kovel.model_event.ItemEvent;
import com.cl.mykowel.model.model_my_kovel.model_user.User;
import com.cl.mykowel.model.model_news.NewsApiResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    //get request "feed/json" отримуєм items news
    @GET("feed/json")
    Call<NewsApiResponse> getItemsNews();

    //реєстрація пост метод повертає назад клас із токеном який
    @POST("register")
    @Multipart
    Call<User> postUserReg(@Part("login") RequestBody login,
                           @Part("name") RequestBody name,
                           @Part("email") RequestBody email,
                           @Part("phone") RequestBody phone,
                           @Part("password") RequestBody password);

    @POST("login")
    @Multipart
    Call<User> postUserLogin(@Part("login") RequestBody login,
                             @Part("password") RequestBody password);

    //get запит для отримання списку всіх контактів
    @GET("contacts/get")
    Call<List<ItemContact>> getItemContact();

    //get request для отримання списку всіх запланованих подій
    @GET("events/get")
    Call<List<ItemEvent>> getItemEvent();

    //get request для отримання списку всіх товарів викладеник bazar
    @GET("market/get")
    Call<List<ItemBazar>> getItemBazar();

    //    @Header("token") String token
    @POST("market/add")
    @Multipart
    Call<ItemBazar> postBazarAddItem(
            @Header("token") String token,
            @Part("title") RequestBody title,
            @Part("description") RequestBody description,
            @Part("price") RequestBody price,
            @Part("category") RequestBody category,
            @Part MultipartBody.Part photo);
}
