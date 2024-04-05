package com.example.inventaryapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inventaryapp.model.Productos
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductosDao {
    @Query("SELECT * FROM PRODUCTOS")
    fun getProductos(): Flow<List<Productos>>

    @Query("SELECT * FROM PRODUCTOS WHERE producto_id = :id")
    fun getProductoById(id:Long): Flow<Productos>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(producto: Productos)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(producto: Productos)

    @Delete
    suspend fun delete(producto: Productos)
}