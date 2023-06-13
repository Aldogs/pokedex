package com.example.pokedex.ui.list

import android.icu.text.CaseMap.Upper
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.models.Pokemon
import com.example.pokedex.repository.PokemonRepository
import com.example.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private var currentIteration = 0

    var pokemonList = mutableStateOf<List<Pokemon>>(listOf())
    var errorLoading = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var lastPage = mutableStateOf(false)

    fun loadNewPokemons() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getPokemonList(20, currentIteration *  20)

            when (result) {
                is Resource.Error -> {
                    errorLoading.value = result.toString()
                    isLoading.value = false
                }
                is Resource.Success -> {
                    lastPage.value = currentIteration * 20 >= result.data!!.count
                    val pokemonEntries = result.data.results.mapIndexed {index, entry ->
                        val number = if (entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        Pokemon(number.toInt(), entry.name.replaceFirstChar { it.uppercase() }, imgUrl)
                    }
                    currentIteration++
                    errorLoading.value = ""
                    isLoading.value = false
                    pokemonList.value += pokemonEntries
                }

                else -> {}
            }
        }
    }
}