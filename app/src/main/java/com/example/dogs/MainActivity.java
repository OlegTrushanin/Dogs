package com.example.dogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {


    ImageView imageView;
    Button button;
    ProgressBar progressBar;

    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.loadDogImage();
        mainViewModel.getData().observe(this, new Observer<Dog>() {
            @Override
            public void onChanged(Dog dog) {
                Glide.with(MainActivity.this) // загружаем картинку в макет
                        .load(dog.getMessage())
                        .into(imageView);

            }
        });

        mainViewModel.getIsLoading().observe(this, new Observer<Boolean>() { // отображение прогресс бара
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        mainViewModel.getInternet().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Toast.makeText(
                        MainActivity.this,
                        "Отсутствует подключение к интернету",
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.loadDogImage();
            }
        });


    }

    private void initView(){
       imageView = findViewById(R.id.imageView);
       button = findViewById(R.id.button);
       progressBar = findViewById(R.id.progressBar);

    }

}