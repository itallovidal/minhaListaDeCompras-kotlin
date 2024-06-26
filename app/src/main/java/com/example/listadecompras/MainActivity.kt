package com.example.listadecompras
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.listadecompras.presentation.screens.home.Home
import com.example.listadecompras.presentation.ui.theme.ListaDeComprasTheme
import com.example.listadecompras.presentation.screens.home.viewModels.ProductsViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ListaDeComprasTheme {
                val colorTwo = ContextCompat.getColor(this@MainActivity, R.color.neutralTwo)
                Surface( color = Color(colorTwo), modifier = Modifier.fillMaxHeight() ) {
                    Home(productsViewModel = ProductsViewModel())
                }
            }
        }
    }
}



@Composable
fun Logo(modifier: Modifier = Modifier){
    val logo = painterResource(R.drawable.logolistadecompras)

    Image(
        modifier = modifier
            .height(80.dp)
            .padding(12.dp),
        contentScale = ContentScale.FillHeight,
        painter = logo,
        contentDescription = null
    )
}









