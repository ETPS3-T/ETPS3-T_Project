package com.example.sivartravel.restservice;

import com.example.sivartravel.entidades.Departamentos;
import com.example.sivartravel.entidades.Lugares;
import com.example.sivartravel.entidades.Municipios;
import com.example.sivartravel.entidades.Usuarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServicioApi {

    /**@POST("/posts")
    @FormUrlEncoded
    Call<Lugares> savePost(@Field("title") String title,
                           @Field("body") String body,
                           @Field("userId") long userId);*/
    @GET("Lugares")
    Call<List<Lugares>> getLugares();

    @GET("Municipios")
    Call<List<Municipios>> getMunicipios();

    @GET("Departamentos")
    Call<List<Departamentos>> getDepartamentos();

    @GET("Usuarios")
    Call<List<Usuarios>> getUsers();

    @POST("Lugares")
    Call<Lugares> setLugares(@Body Lugares lugares);

}
