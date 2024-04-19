package com.example.inventaryapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventaryapp.model.Productos
import com.example.inventaryapp.repository.repositoryProducto
import com.example.inventaryapp.state.StateProducto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewModelProduct @Inject constructor(private val repository: repositoryProducto) :
    ViewModel() {
    private val _productList = MutableStateFlow<List<Productos>>(emptyList())
    val productlist = _productList.asStateFlow()

    var state by mutableStateOf(StateProducto())
        private set

    var showAlert by mutableStateOf(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllProducts().collect { item ->
                if (item.isEmpty()) {
                    _productList.value = emptyList()
                } else {
                    _productList.value = item
                }
            }


        }
    }


    fun getProductById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProductById(id).collect { item ->
                state = state.copy(
                    nombre = item.nombre,
                    categoria = item.categoria,
                    precios = item.precios,
                    stock = item.stock,
                    foto = item.foto

                )
            }
        }
    }

    fun addProduct(product: Productos) = viewModelScope.launch { repository.addProducts(product) }
    fun updateProducto(product: Productos) =
        viewModelScope.launch { repository.updateProducts(product) }

    fun deleteProducto(product: Productos) =
        viewModelScope.launch { repository.deleteProduct(product) }

    fun closeAlert() {
        showAlert = false
    }

}