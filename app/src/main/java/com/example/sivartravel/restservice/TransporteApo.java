package com.example.sivartravel.restservice;

import com.example.sivartravel.entidades.Transporte;
import com.example.sivartravel.entidades.Lugares;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TransporteApo {


    @GET("Transporte")
    Call<List<Transporte>> getTransportes();

    @POST("Transporte")
    Call<Transporte> addTransporte(@Body Transporte transporte);



}
