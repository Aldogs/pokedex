package com.example.pokedex.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun DetailsScreen(
    name: String,
    navController : NavController,
    imgSize : Dp = 200.dp,
    viewModel : PokemonDetailsViewModel = hiltViewModel()
) {
    val pokemonDetails = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemon(name)
    }.value
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 16.dp)
    ) {
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier
            .fillMaxSize()) {
            if (pokemonDetails is Resource.Success<*>) {
               /* pokemonDetails.data?.sprites?.let {
                    AsyncImage(
                        model = it.frontDefault,
                        "Image off pokemon",
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color.Gray.copy(alpha = 0.1f))

                    )
                }*/
            }
        }
    }
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        //PokemonDetails(navController = navController)
    }
}

