package com.example.dogs;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("image") // указываем на то что получаем данные из сети. В кавычках указываем переменную часть URL
    Single<Dog> loadDogImage();
}
