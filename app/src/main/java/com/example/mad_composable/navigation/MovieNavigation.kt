package com.example.mad_composable.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testapp.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController  = navController,
            startDestination = "homescreen"
        ){
        composable("homescreen") { HomeScreen(navController) }
        composable(route = "detailscreen/{movieId}",
            arguments = listOf(
                navArgument(name = "movieId"){
                    type = NavType.StringType
                }
            )

        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            DetailScreen(movieId = movieId)
        }
    }

}