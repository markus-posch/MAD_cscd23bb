package com.example.mad_composable.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mad_composable.navigation.MovieScreens
import com.example.mad_composable.viewmodels.FavoritViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    val favoritViewModel: FavoritViewModel = viewModel()
    NavHost(navController  = navController,
            startDestination = MovieScreens.Homescreen.name
        ){
        composable(MovieScreens.Homescreen.name) { HomeScreen(navController, favoritViewModel ) }
        composable(route = MovieScreens.Detailscreen.name +"/{movieId}",
            arguments = listOf(
                navArgument(name = "movieId"){
                    type = NavType.StringType
                }
            )

        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            DetailScreen(movieId = movieId, navController = navController, favoritViewModel)
        }
        composable(MovieScreens.Favoritscreen.name) { Favoritscreen(navController, favoritViewModel) }

    }

}