package com.example.mad_composable.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mad_composable.navigation.MovieScreens
import com.example.mad_composable.viewmodels.FavoritViewModel
import com.example.mad_composable.widgets.FavoritIcon
import com.example.mad_composable.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Composable
fun Favoritscreen(navController: NavController, FavoritViewModel: FavoritViewModel = viewModel()) {
    Scaffold(
        topBar = {

            TopAppBar(
                backgroundColor = Color.Cyan,
                elevation = 3.dp
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBack",
                        modifier = Modifier.clickable { navController.popBackStack() },
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Favorit Movies", style = MaterialTheme.typography.h6, color = Color.White)
                }
            }

        }
    ) {
            MainContent(
            FavoritViewModel.getFavorits(),
            navController = navController,
        )

    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent(
    movieslist: List<Movie>,
    navController: NavController,
) {
    LazyColumn {
        items(movieslist) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { movieId -> navController.navigate(MovieScreens.Detailscreen.name + "/$movieId") },
            )
        }
    }
}