package com.example.inventaryapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inventaryapp.model.categoria
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriaDao {
    @Query("SELECT * FROM CATEGORIAS")
    fun getCategorias(): Flow<List<categoria>>

    @Query("SELECT * FROM CATEGORIAS WHERE id_categoria = :id")
    fun getCategoriasById(id:Long): Flow<categoria>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(c: categoria)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(c: categoria)

    @Delete
    suspend fun delete(c: categoria)
}