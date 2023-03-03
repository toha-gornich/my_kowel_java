package com.cl.mykowel.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstanceKowelMedia {

    private static Retrofit retrofit = null;
    //path kowel media api
    private static String BASE_URL_KOWEL_ONLINE = "https://kovel.media/";
    //get request for get service


    public static ApiService getNewsService() {
        if (retrofit == null) {
    //формується http request з BASE_URL і ддругої половинки урл, повертає NewsApiService а саме retrofit
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL_KOWEL_ONLINE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
