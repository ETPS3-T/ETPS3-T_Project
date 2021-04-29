package com.example.sivartravel.restservice;

import com.example.sivartravel.Constantes;
import com.example.sivartravel.entidades.Lugares;
import com.example.sivartravel.entidades.Transporte;
import com.example.sivartravel.Constantes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ServicioApi getSOService() {
        return RetrofitClient.getClient(Constantes.BASE_URL).create(ServicioApi.class);
    }

    public static TransporteApo getSOTransporte() {
        return RetrofitClient.getClient(Constantes.BASE_URL).create(TransporteApo.class);
    }
}
