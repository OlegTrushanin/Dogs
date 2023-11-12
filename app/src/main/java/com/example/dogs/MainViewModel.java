package com.example.dogs;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";
    private static final String MESSAGE_JS = "message";
    private static final String STATUS_JS = "status";

    private MutableLiveData <Dog> data = new MutableLiveData<>();


    public LiveData <Boolean> getInternet() {
        return internet;
    }

    private MutableLiveData <Boolean> internet = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    private MutableLiveData <Boolean> isLoading = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LiveData<Dog> getData() {
        return data;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

   public void loadDogImage(){

       Disposable disposable = loadDogRx()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .doOnSubscribe(new Consumer<Disposable>() { // колбэк. Код в методе выполнится когда произойдет подписка
                   @Override
                   public void accept(Disposable disposable) throws Throwable {
                       isLoading.setValue(true);
                   }
               })
               .doAfterTerminate(new Action() { //колбэк. Код в методе выолнится когда загрузка будет заверщена
                   @Override
                   public void run() throws Throwable {
                       isLoading.setValue(false);
                   }
               })
               .subscribe(new Consumer<Dog>() {
                   @Override
                   public void accept(Dog dog) throws Throwable {

                       data.setValue(dog);

                   }
               }, new Consumer<Throwable>() {
                   @Override
                   public void accept(Throwable throwable) throws Throwable {

                       internet.setValue(true);


                   }
               });

       compositeDisposable.add(disposable);


   }



    private Single<Dog> loadDogRx(){

        return ApiFactory.getApiService().loadDogImage();


    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
