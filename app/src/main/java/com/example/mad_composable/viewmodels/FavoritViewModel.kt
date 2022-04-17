package com.example.mad_composable.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.testapp.models.Movie

class FavoritViewModel: ViewModel() {
    private var favorites = mutableStateListOf<Movie>()

    fun addRemoveFavorit(movie: Movie){
        if(movie !in favorites) {
            favorites.add(movie)
        }
        else {
            favorites.remove(movie)
        }
    }

    fun getFavorits(): List<Movie>{
        return favorites
    }


    fun checkIfFavorit(movie: Movie): Boolean {
        return movie in favorites
    }
}