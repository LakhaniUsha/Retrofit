package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Poekrecyadapter adapter;
    ArrayList<Pokemon> pokearray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GetDatasevice service = RetrofitClientInstance.getRetrofitInstance().create(GetDatasevice.class);

      /* Call<List<Pokemon>> call = service.getPokemons();

        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {

                    generateData(response.body());

            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Try again Later",Toast.LENGTH_SHORT).show();

            }
        }); */


      Call<List<PokemonPojo>> call = service.getPokemonObject();

      call.enqueue(new Callback<List<PokemonPojo>>() {
          @Override
          public void onResponse(Call<List<PokemonPojo>> call, Response<List<PokemonPojo>> response) {


              PokemonPojo pojo = (PokemonPojo) response.body();

              try {
                  pokearray = new ArrayList<>(pojo.getPokemon());
                     generateData(pokearray);

              }catch (NullPointerException e)
              {
                  System.out.println(e.getMessage());
              }

          }

          @Override
          public void onFailure(Call<List<PokemonPojo>> call, Throwable t) {

          }
      });
    }




    public void generateData(ArrayList<Pokemon> pokemonList) /*List<Pokemons> pokemonlist*/
    {
       /* ArrayList<Pokemon> pokemons = (ArrayList<Pokemon>) pokemonList; */
        adapter = new Poekrecyadapter(pokemonList,getApplicationContext());
        @SuppressLint("WrongConstant") LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = findViewById(R.id.recycle_poke);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}
