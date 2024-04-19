package com.example.inventaryapp.ui.productos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.inventaryapp.components.CardProduct
import com.example.inventaryapp.navigation.ViewsScreens
import com.example.inventaryapp.viewmodel.viewModelProduct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductView(navController: NavHostController, productVM: viewModelProduct) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Productos") },

                actions = {
                    IconButton(onClick = {
                        navController.navigate(ViewsScreens.AddProductoView.name)
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
            )
        }
    ) { padd ->
        Column(
            modifier = Modifier.padding(padd),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val productList by productVM.productlist.collectAsState()
            LazyColumn {
                this.items(productList) { item ->
                    CardProduct(
                        nombre = item.nombre,
                        categoria = item.categoria,
                        cantidad = item.stock.toString(),
                        precio = item.precios.toString(),
                        foto = item.foto,
                        onDetails = {navController.navigate("DetailsProducto/${item.id}") }) {
                        productVM.deleteProducto(item)
                    }
                }
            }
        }
    }
}
