package com.example.mad_composable


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mad_composable.ui.theme.MAD_ComposableTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testapp.models.Movie
import com.example.testapp.models.getMovies
import com.example.mad_composable.screens.HomeScreen
import com.example.mad_composable.screens.MyNavigation

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                //HomeScreen(movieList = getMovies())
                MyNavigation()
                Log.d("test", "test1")

        }
    }
}
/*
@Composable
fun MyApp(content: @Composable () -> Unit) {
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
            content()
        }
    }
}



@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {

    }
}

*/
