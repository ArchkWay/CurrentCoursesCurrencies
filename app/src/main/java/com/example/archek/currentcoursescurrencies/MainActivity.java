package com.example.archek.currentcoursescurrencies;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList <ObjectResponse> todayCurrencies = new ArrayList <>();
    ArrayList <ObjectResponse> yesterdayCurrencies = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView = findViewById( R.id.recyclerView );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat( "dd.MM.yyyy" );
        Date dater = new Date();
        final String today = dateFormat.format( dater );
        String yesterday = today.substring( 0, 2 );
        int temp = Integer.parseInt( yesterday );
        yesterday = (temp - 1) + today.substring( 2 );

        setTitle( "Date: " + today );


        // Response

        CccApp.getCurService().getData( yesterday ).enqueue( new Callback <ObjectListResponse>() {
            @Override
            public void onResponse(Call <ObjectListResponse> call, Response <ObjectListResponse> response) {
                if (response.body() != null) {
                    yesterdayCurrencies = response.body().getCurrencies();
                }


                CccApp.getCurService().getData( today ).enqueue( new Callback <ObjectListResponse>() {
                    @Override
                    public void onResponse(Call <ObjectListResponse> call, Response <ObjectListResponse> response) {
                        if (response.body() != null) {
                            todayCurrencies = response.body().getCurrencies();
                        }
                        for (int i = 0; i < todayCurrencies.size(); i++) {
                            Log.d("44__", String.valueOf( i ));
                            int todayValue = Integer.getInteger( todayCurrencies.get( i ).getValue() );
                            int yesterdayValue = Integer.getInteger( yesterdayCurrencies.get( i ).getValue() );
                            if (todayValue > yesterdayValue) {
                                todayCurrencies.get( i ).setRise( true );
                            }
                        }
                        RecAdapter adapter = new RecAdapter( todayCurrencies );
                        recyclerView.setAdapter( adapter );
                    }

                    @Override
                    public void onFailure(Call <ObjectListResponse> call, Throwable t) {
                        Toast.makeText( MainActivity.this, "Error", Toast.LENGTH_SHORT ).show();
                    }
                } );
            }

            @Override
            public void onFailure(Call <ObjectListResponse> call, Throwable t) {
                Toast.makeText( MainActivity.this, "Error", Toast.LENGTH_SHORT ).show();
            }
        } );

    }
}
