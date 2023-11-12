package com.example.dogs;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/"; // базовая часть URL, всегда должна заканчиваться косой чертой
    private static ApiService apiService;

    public static ApiService getApiService(){

        if(apiService == null){
            Retrofit retrofit = new Retrofit.Builder() // создаем экземпляр класса Retrofit
                    .baseUrl(BASE_URL)   // передаем URL
                    .addConverterFactory(GsonConverterFactory.create()) // преобразует JSON в экземпляр класса Dog
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // нужен чтобы возвращаемый тип был Single
                    .build();
            apiService = retrofit.create(ApiService.class);
        }

        return apiService;

    }

}
