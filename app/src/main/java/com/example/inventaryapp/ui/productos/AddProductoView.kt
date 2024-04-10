package com.example.inventaryapp.ui.productos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inventaryapp.components.SpinnerCategoria
import com.example.inventaryapp.viewmodel.viewModelProduct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductoView(navController: NavController, productoVM: viewModelProduct) {
    var nombre by remember { mutableStateOf("") }
    var precios by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf("") }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nuevo Producto") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (nombre.isEmpty() || precios.isEmpty() || foto.isEmpty() || stock.isEmpty()) {

                        } else {

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
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
            )

            OutlinedTextField(
                value = precios,
                onValueChange = { precios = it },
                label = { Text(text = "Precio") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )
            SpinnerCategoria()

            OutlinedTextField(
                value = stock,
                onValueChange = { stock = it },
                label = { Text(text = "Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )


        }
//        if (usersVM.showAlert) {
//            Alert(
//                title = "Alerta",
//                message = "Nombre y/o Descripcion vacios",
//                confirmText = "Aceptar",
//                onConfirmClick = {  }) {
//
//            }
//        }
    }
}