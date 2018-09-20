package com.example.archek.currentcoursescurrencies;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface CurService {

    @GET("scripts/XML_daily_eng.asp")
    Call<ObjectListResponse> getData(@Query("date_req") String dateReg);


}
