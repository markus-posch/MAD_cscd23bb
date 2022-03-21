package com.example.mad_composable.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mad_composable.MovieRow
import com.example.mad_composable.ui.theme.MAD_ComposableTheme
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    var showMenu by remember {
        mutableStateOf(false)
    }


    MAD_ComposableTheme {
        // A surface container using the 'background' color from the theme
        Scaffold(
            //modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text(text = "Movies") },
                    actions = {
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "Expand Menu"
                            )
                        }
                        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                            DropdownMenuItem(onClick = { /*TODO*/ }) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = "Favorites",
                                        Modifier.padding(4.dp)
                                    )
                                    Text(text = "Favorites", modifier = Modifier.width(100.dp))
                                }
                            }
                        }

                    })

            }
        ) {
            MainContent(getMovies(), navController)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(movieList: List<Movie>, navController: NavController) {
    LazyColumn {
        items(movieList) { movie ->
            MovieRow(movie) { movieId ->
                Log.d("MainContent", "My value: $movieId")
                navController.navigate("detailscreen/$movieId")
            }
        }
    }
}