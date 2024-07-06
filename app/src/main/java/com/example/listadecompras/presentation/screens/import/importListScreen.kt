package com.example.listadecompras.presentation.screens.import
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.listadecompras.presentation.ui.globalComposables.Logo
import com.example.listadecompras.presentation.ui.theme.neutralOne
import com.example.listadecompras.presentation.ui.theme.neutralThree
import com.example.listadecompras.utility.getGradient
import com.example.listadecompras.viewmodels.ProductsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportListScreen(productsViewModel: ProductsViewModel, navController: NavController){
    var listToImport by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(getGradient(neutralOne, neutralThree))
        .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally){

       Logo()
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 18.dp)){
            Text(text = "Cole a lista abaixo.", color = Color.White, modifier = Modifier.padding(bottom = 14.dp))
            TextField(
                value = listToImport,
                onValueChange = {
                    listToImport = it
                },
                placeholder = { Text(text = "1 arroz \n" +
                        "feijão \n" +
                        "2 batatas", color = Color.Gray) },
                textStyle = TextStyle(color = Color.White, ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF4F4F52),
                ),
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center){
                Button(
                    modifier = Modifier.padding(24.dp),
                    onClick = {
                        productsViewModel.importNewList(listToImport)
                        navController.navigate("home")
                    },
                    shape = RoundedCornerShape(8),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF379DF1),
                    )
                ) {
                    Text(text = "Importar lista",)
                }
            }
        }
    }
}
