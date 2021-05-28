package com.example.sivartravel.restservice;

import com.example.sivartravel.entidades.Transporte;
import com.example.sivartravel.entidades.Lugares;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

import java.util.List;

public interface TransporteApo {


    @GET("Transporte")
    Call<List<Transporte>> getTransportes();

    @POST("Transporte")
    Call<Transporte> addTransporte(@Body Transporte transporte);

    @DELETE("Transporte/{id}")
    Call<Transporte> deleteTransporte(@Path("id") Integer id);

    @PUT("Transporte")
    Call<Transporte> UpdateTransporte(@Body Transporte transporte);

    @GET("Transporte/LugaresMax1/{id}")
    Call<List<Transporte>> getTransporte(@Path("id") int id);

}
