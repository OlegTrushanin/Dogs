package com.example.dogs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://dog.ceo/api/breeds/image/random";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDogImage();
    }

    private void loadDogImage(){

       new Thread(new Runnable() { // вся работа с интернетом выполняется в фоновом потоке
            @Override
            public void run() {

                try {
                    URL url = new URL(BASE_URL); // получаем адрес в специальном формате
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // открываем соединение
                    InputStream inputStream = httpURLConnection.getInputStream(); // считываем информацию побайтово
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // считываем посимвольно
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // считываем построкова
                    String result = bufferedReader.readLine(); // результат запроса
                    Log.d("MainActivity1", result);


                } catch (Exception e) {
                    Log.d("MainActivity", e.toString());
                }


            }
        }).start();


    }
}