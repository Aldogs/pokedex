package com.example.pokedex.ui.details

import androidx.lifecycle.ViewModel
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.repository.PokemonRepository
import com.example.pokedex.util.Resource
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    suspend fun getPokemon(name: String): Resource<Pokemon> {
        return  repository.getPokemon(name)

    }
}