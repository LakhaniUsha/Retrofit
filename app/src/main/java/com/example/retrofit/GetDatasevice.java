package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDatasevice {

    @GET("Vy2abloQD")
    Call<List<Pokemon>> getPokemons();


    @GET("E14trR2lD")
    Call<List<PokemonPojo>> getPokemonObject();

}



