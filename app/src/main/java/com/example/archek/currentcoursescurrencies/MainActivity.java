package com.example.archek.currentcoursescurrencies;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    ArrayList<Valute> valutes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dater = new Date();
        String date = dateFormat.format( dater );
        setTitle("Date: " + date);

        valutes = new ArrayList<>(); // Список

        // RV
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Установка менеджера
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // Установка анимации


        // Ответ
        App.getApi().getData(date).enqueue(new Callback<ValCurs>() {
            @Override
            public void onResponse(Call<ValCurs> call, Response<ValCurs> response) {
                valutes = response.body().getValute();

                adapter = new RecAdapter(valutes); // Объект RV-адаптера
                recyclerView.setAdapter(adapter); // Установка RV-адаптера
            }

            @Override
            public void onFailure(Call<ValCurs> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка соединения", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
