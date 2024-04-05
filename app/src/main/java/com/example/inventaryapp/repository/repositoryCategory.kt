package com.example.inventaryapp.repository

import com.example.inventaryapp.model.categoria
import com.example.inventaryapp.room.dao.CategoriaDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class repositoryCategory @Inject constructor(private val categoryDao: CategoriaDao) {

    suspend fun addCategory(category:categoria) = categoryDao.insert(category)
    suspend fun updateCategory(category:categoria) = categoryDao.update(category)
    suspend fun deleteCategory(category:categoria) = categoryDao.delete(category)
    fun getAllCronos(): Flow<List<categoria>> = categoryDao.getCategorias().flowOn(Dispatchers.IO).conflate()
}