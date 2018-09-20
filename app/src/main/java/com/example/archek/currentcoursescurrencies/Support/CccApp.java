package com.example.archek.currentcoursescurrencies;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class CccApp extends Application {

    private static CurService curService;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.cbr.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        curService = retrofit.create(CurService.class);
    }

    public static CurService getCurService() {
        return curService;
    }
}