package com.example.inventaryapp.ui.categoria

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inventaryapp.components.Alert
import com.example.inventaryapp.model.categoria
import com.example.inventaryapp.viewmodel.viewModelCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCategoryView(navController: NavController, categoryVM: viewModelCategory){
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nueva Categoria") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (title.isEmpty() || description.isEmpty()){
                            categoryVM.showAlert = true
                        }else{
                            categoryVM.addCategory(
                                categoria(
                                    nombre = title,
                                    descripcion = description
                                )
                            )
                            navController.popBackStack()
                        }

                    }) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = "")
                    }
                }
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Nombre Categoria") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Descripcion Categoria") },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )
        }

        if (categoryVM.showAlert){
            Alert(
                title = "Alerta",
                message = "Nombre y/o Descripcion vacios",
                confirmText = "Aceptar",
                onConfirmClick = { categoryVM.closeAlert() }) {

            }
        }

    }
}