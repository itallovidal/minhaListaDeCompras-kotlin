package com.example.listadecompras
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listadecompras.presentation.screens.history.HistoryScreen
import com.example.listadecompras.presentation.screens.history.HistoryViewModel
import com.example.listadecompras.presentation.screens.historyDetails.HistoryDetailsScreen
import com.example.listadecompras.presentation.screens.home.Home
import com.example.listadecompras.presentation.ui.theme.ListaDeComprasTheme
import com.example.listadecompras.viewmodels.ProductsViewModel
import com.example.listadecompras.presentation.screens.import.ImportListScreen
import com.example.listadecompras.presentation.ui.theme.neutralOne
import com.example.listadecompras.presentation.ui.theme.neutralTwo
import com.example.listadecompras.presentation.ui.theme.primary
import com.example.listadecompras.utility.Ktor
import com.example.listadecompras.viewmodels.AppDatabase


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeComprasTheme {
                var selectedScreen by remember {
                    mutableStateOf("home")
                }

                val ktor = Ktor()
                val appDatabase = AppDatabase()

                val productsViewModel = ProductsViewModel(ktor)
                val historyViewModel = HistoryViewModel(ktor)

                val navController = rememberNavController()

                Surface( color = neutralTwo, modifier = Modifier.fillMaxHeight() ) {
                    Column {
                        NavHost(navController = navController, startDestination = "home", Modifier.weight(1f) ){
                            composable("home"){
                                Home(productsViewModel, appDatabase)
                            }
                            composable("importList"){
                                ImportListScreen(productsViewModel, navController)
                            }
                            composable("history"){
                                HistoryScreen(historyViewModel, navController, appDatabase )
                            }
                            composable("historyDetails/{listID}"){ backStackEntry ->
                                HistoryDetailsScreen(historyViewModel, backStackEntry.arguments?.getString("listID")?.toInt())
                            }
                        }
                        BottomAppBar(
                            containerColor = neutralOne,
                            actions = {
                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                                label = {
                                        Text(
                                            text="Home" ,
                                            color = if (selectedScreen == "home") primary else Color.Gray
                                        )
                                },
                                selected = selectedScreen == "home",
                                onClick = {
                                    navController.navigate("home")
                                    selectedScreen = "home"
                                },
                                icon = {
                                    Icon(
                                        Icons.Filled.Home,
                                        contentDescription = null,
                                        tint = if (selectedScreen == "home") primary else Color.Gray
                                    )
                                })
                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),                                label = {
                                    Text(text="Importar Lista",
                                        color = if (selectedScreen == "importList") primary else Color.Gray
                                    )
                                },
                                selected = selectedScreen == "importList",
                                onClick = {
                                    navController.navigate("importList")
                                    selectedScreen = "importList"
                                 },
                                icon = {
                                    Icon(
                                        Icons.Filled.Add,
                                        contentDescription = null,
                                        tint = if (selectedScreen == "importList") primary else Color.Gray
                                    )
                                })
                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),                                label = {
                                    Text(
                                        text="Histórico",
                                        color = if (selectedScreen == "history") primary else Color.Gray
                                        )
                                },
                                selected = selectedScreen == "history",
                                onClick = {
                                    navController.navigate("history")
                                    selectedScreen = "history"
                                },
                                icon = {
                                    Icon(
                                        Icons.Filled.List,
                                        contentDescription = null,
                                        tint = if (selectedScreen == "history") primary else Color.Gray
                                    )
                            })
                        })
                    }
                }
            }
        }
    }
}













