@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.inventaryapp.ui.productos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.inventaryapp.R
import com.example.inventaryapp.viewmodel.viewModelProduct
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsProducto(navController: NavHostController, productVM: viewModelProduct, id: Long) {
    var nombre by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var foto by remember { mutableStateOf("") }

    val state = productVM.state
    nombre = state.nombre
    categoria = state.categoria
    cantidad = state.stock.toString()
    precio = state.precios.toString()
    foto = state.foto

    LaunchedEffect(Unit) {
        productVM.getProductById(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalle Producto") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },

                )
        }
    ) { padd ->
        Column(
            modifier = Modifier
                .padding(padd)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = foto,
                placeholder = painterResource(id = R.drawable.ic_image),
                error = painterResource(id = R.drawable.errorphoto),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1280f / 692f)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Nombre; ${nombre}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraLight)
                Text(text = "Categoria; ${categoria}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraLight)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Cantidad; ${cantidad}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray)
                Text(text = "Precio; ${precio}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Gray)
            }

            Button(onClick = { navController.navigate("EditProductoView/${id}") }) {
                Text(text = "Editar Producto")
            }
        }
    }
}