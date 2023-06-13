package com.example.pokedex.ui.list

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokedex.models.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun ListScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        PokemonList(navController = navController)
    }
}

@Composable
fun PokemonList(
    navController : NavController,
    viewModel : PokemonListViewModel = hiltViewModel()

) {
    val pokemonList by remember {
        viewModel.pokemonList
    }
    val lastPage by remember {
        viewModel.lastPage
    }
    val errorLoading by remember {
        viewModel.errorLoading
    }
    val isLoading by remember {
        viewModel.isLoading
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pokémon list")
                }
            )
        }
    ) { padding ->
        viewModel.loadNewPokemons()
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(pokemonList.size) {
                if (it >= pokemonList.size - 1 && !lastPage && !isLoading) {
                    viewModel.loadNewPokemons()
                }
                PokemonEntry(entry = pokemonList[it], navController = navController)
            }
        }
    }
}


@Composable
fun PokemonEntry(
    entry: Pokemon,
    navController: NavController,
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                navController.navigate(
                    "details_screen/${entry.name}"
                )
            }
    ) {
        Box(modifier = Modifier.size(70.dp, 34.dp)) {
            Text(
                "#${entry.pokedexNumber}",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(4.dp)
            )
        }
        Text(
            entry.name.replaceFirstChar {
                it.uppercase()
            },
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(horizontal = 10.dp)
        )

        Spacer(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Green))
        AsyncImage(
            model = entry.imageUrl,
            "Image off pokemon",
            modifier = Modifier
                .padding(end = 15.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.1f))

        )
    }

}

