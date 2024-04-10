package com.example.inventaryapp.repository

import com.example.inventaryapp.model.Productos
import com.example.inventaryapp.room.dao.ProductosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class repositoryProducto @Inject constructor(private val productDao:ProductosDao) {

    suspend fun addProducts(product: Productos) = productDao.insert(product)
    suspend fun updateProducts(product: Productos) = productDao.update(product)
    suspend fun deleteProduct(product: Productos) = productDao.delete(product)
    fun getAllProducts(): Flow<List<Productos>> = productDao.getProductos().flowOn(Dispatchers.IO).conflate()
    fun getProductById(id:Long): Flow<Productos> = productDao.getProductoById(id).flowOn(
        Dispatchers.IO).conflate()
}