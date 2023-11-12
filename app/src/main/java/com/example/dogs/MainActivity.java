package com.example.dogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;



public class MainActivity extends AppCompatActivity {


    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.loadDogImage();
        mainViewModel.getData().observe(this, new Observer<Dog>() {
            @Override
            public void onChanged(Dog dog) {
                Log.d("MainActivity",dog.toString());
            }
        });

    }


}