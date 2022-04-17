package com.example.mad_composable.widgets

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.example.mad_composable.R
import com.example.mad_composable.navigation.MovieScreens
import com.example.mad_composable.viewmodels.FavoritViewModel
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies

//Test weil das Automatische entfernen einer Card nicht ging
//Fehler war allerdings das ich keine mutableSTATEList genommen hatte
/*
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Movielist(list: List<Movie>,
              onItemClick: (String) -> Unit = {},
              onDeleteClick: (Movie) -> Unit = {},
              FavoritViewModel: FavoritViewModel = viewModel()
              ){
    LazyColumn {
        items(list) { movie ->
            MovieRow(movie = movie,
                onItemClick = {movieId -> onItemClick(movieId)},
            ){
                FavoritIcon(movie,FavoritViewModel.checkIfFavorit(movie)){ movie ->
                    onDeleteClick(movie)
                }
            }
        }
    }

}
*/
@ExperimentalAnimationApi
@Composable
fun MovieRow(movie: Movie,
             onItemClick: (String) -> Unit = {},
             content: @Composable () -> Unit = {}
             ) {

    var expandMovieRow by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onItemClick(movie.id) },

        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(6.dp),
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                //horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(6.dp),
            ) {
                Surface(
                    modifier = Modifier
                        .size(95.dp)
                        .padding(3.dp),
                ) {

                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current).data(data = movie.images[0])
                                .apply(block = fun ImageRequest.Builder.() {
                                    transformations(CircleCropTransformation())
                                }).build()
                        ),
                        contentDescription = "test"
                    )

                }
                Column {
                    Text(text = movie.title, style = MaterialTheme.typography.h6)
                    Text("Director: ${movie.director}", style = MaterialTheme.typography.body2)
                    Text("Released: ${movie.year}", style = MaterialTheme.typography.body2)

                    AnimatedVisibility(
                        visible = expandMovieRow,
                        enter = slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                        ) + fadeIn(),
                        exit = slideOutHorizontally(),
                    )
                    {
                        Column(modifier = Modifier.padding(4.dp)) {

                            Text("Plot: ${movie.plot}", style = MaterialTheme.typography.body2)
                            Divider(
                                color = Color.LightGray,
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                            )
                            Text("Actors: ${movie.actors}", style = MaterialTheme.typography.body2)
                            Text("Rating: ${movie.rating}", style = MaterialTheme.typography.body2)
                        }
                    }

                    Icon(
                        imageVector = if (expandMovieRow) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        contentDescription = "KeyboardArrowUp",
                        modifier = Modifier.clickable { expandMovieRow = !expandMovieRow },
                    )

                }

            }
            content()
        }
    }
}


@Composable
fun HorizontalScrollImageView(movie: Movie) {
    LazyRow() {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .size(240.dp)
                    .fillMaxWidth(),
                elevation = 3.dp
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = image)
                            .build()
                    ),
                    modifier = Modifier.size(240.dp),
                    contentDescription = "test"
                )
            }
        }
    }
}

@Composable
fun FavoritIcon(movie: Movie,
                isFavorit:Boolean,
                onDeleteClick: (Movie) -> Unit = {}){

    var testFavorit by remember {
        mutableStateOf(isFavorit)
    }

    Icon(
        imageVector = if (testFavorit) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = "FavoriteBorder",
        modifier = Modifier.padding(5.dp)
            .clickable {onDeleteClick(movie)
                    testFavorit = !testFavorit
                       },
        tint = Color.Cyan
    )
}

