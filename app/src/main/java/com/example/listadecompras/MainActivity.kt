package com.example.listadecompras
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listadecompras.presentation.screens.home.Home
import com.example.listadecompras.presentation.ui.theme.ListaDeComprasTheme
import com.example.listadecompras.presentation.screens.home.viewModels.ProductsViewModel
import com.example.listadecompras.presentation.screens.import.ImportListScreen
import java.util.logging.Handler


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeComprasTheme {
                var selectedScreen by remember {
                    mutableStateOf("home")
                }

                val productsViewModel = ProductsViewModel()
                val navController = rememberNavController()
                val colorTwo = ContextCompat.getColor(this@MainActivity, R.color.neutralTwo)

                Surface( color = Color(colorTwo), modifier = Modifier.fillMaxHeight() ) {
                    Column {
                        NavHost(navController = navController, startDestination = "home", Modifier.weight(1f) ){
                            composable("home"){
                                Home(productsViewModel)
                            }
                            composable("importList"){
                                ImportListScreen(productsViewModel, navController)
                            }
                        }
                        BottomAppBar(actions = {
                            NavigationBarItem(
                                label = {
                                        Text(text="Home")
                                },
                                selected = selectedScreen == "home",
                                onClick = {
                                    navController.navigate("home")
                                    selectedScreen = "home"
                                },
                                icon = {
                                    Icon(Icons.Filled.Home, contentDescription = null)
                                })
                            NavigationBarItem(
                                label = {
                                    Text(text="Importar Lista")
                                },
                                selected = selectedScreen == "importList",
                                onClick = {
                                    navController.navigate("importList")
                                    selectedScreen = "importList"
                                 },
                                icon = {
                                    Icon(Icons.Filled.Add, contentDescription = null)
                                })
                        })
                    }
                }
            }
        }
    }
}













