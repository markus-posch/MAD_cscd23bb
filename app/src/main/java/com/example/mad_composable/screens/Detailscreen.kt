package com.example.mad_composable.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.testapp.models.getMovies

@Composable
fun DetailScreen(movieId: String? = getMovies()[0].id) {
    Text(text = "Hello detailscreen $movieId")
}