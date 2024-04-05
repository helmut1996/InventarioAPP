package com.example.inventaryapp.ui.categoria

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.inventaryapp.navigation.ViewsScreens
import com.example.inventaryapp.viewmodel.viewModelCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryView(navigationController: NavController,categoryVM:viewModelCategory){
    Scaffold(
        topBar =
        {
            TopAppBar(
                title = { Text(text = "Categorias") },

                actions = {
                    IconButton(onClick = {
                        navigationController.navigate(ViewsScreens.AddCategoriaView.name)
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
            )
        }
    ) {padd->
        Column(
            modifier = Modifier.padding(padd),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home View")
        }
    }
}
