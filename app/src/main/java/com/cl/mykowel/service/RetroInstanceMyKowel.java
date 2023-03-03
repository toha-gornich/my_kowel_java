package com.cl.mykowel.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstanceMyKowel {

    private static Retrofit retrofit = null;
    //path my kowel api
    private  static String BASE_URL_MY_KOWEL = "http://mykowel.pp.ua:8000/";
//get request for get service

    public static ApiService getKowelService() {
// додається можливість відстежувати помилки
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);


        if (retrofit == null) {
            //формується http request з BASE_URL і ддругої половинки урл, повертає NewsApiService а саме retrofit
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL_MY_KOWEL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }

}
