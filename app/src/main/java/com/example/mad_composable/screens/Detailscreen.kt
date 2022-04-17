package com.example.mad_composable.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mad_composable.viewmodels.FavoritViewModel
import com.example.mad_composable.widgets.FavoritIcon
import com.example.mad_composable.widgets.HorizontalScrollImageView
import com.example.mad_composable.widgets.MovieRow
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

@Composable
fun DetailScreen(
    movieId: String? = getMovies()[0].id,
    navController: NavController,
    FavoritViewModel: FavoritViewModel = viewModel()
) {
    val movie = getmovie(movieId = movieId)
    Scaffold(
        topBar = {

            TopAppBar(backgroundColor = Color.Cyan,
                    elevation = 3.dp
                ){
                Row{
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBack",
                        modifier = Modifier.clickable {  navController.popBackStack() },
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = movie.title, style = MaterialTheme.typography.h6, color = Color.White)


                }
            }

        }
    ) {
        MainContent(movie, FavoritViewModel)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContent(movie: Movie, FavoritViewModel: FavoritViewModel = viewModel()) {
    Surface(modifier = Modifier
        .fillMaxWidth()) {
        Column {
            MovieRow(movie = movie){
                FavoritIcon(movie,FavoritViewModel.checkIfFavorit(movie)){ movie ->
                    FavoritViewModel.addRemoveFavorit(movie)
                }
            }
            Spacer(modifier = Modifier.height(3.dp))
            Divider()
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "Movies Images",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h5
            )
            HorizontalScrollImageView(movie = movie)
        }
    }
}

fun getmovie(movieId: String?) : Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}