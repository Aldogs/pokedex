package com.example.pokedex.repository

import com.example.pokedex.data.remote.PokeApi
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.remote.responses.PokemonList
import com.example.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
    suspend fun getPokemon(name: String) : Resource<Pokemon> {
        val response : Pokemon = try {
            api.getPokemon(name)
        } catch (e: Exception) {
            return Resource.Error<Pokemon>(null, e.toString())
        }
        return Resource.Success<Pokemon>(response)
    }

    suspend fun getPokemonList(limit : Int, offset : Int) : Resource<PokemonList> {
        val response : PokemonList = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error<PokemonList>(null, e.toString())
        }
        return Resource.Success<PokemonList>(response)
    }
}