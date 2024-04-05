package com.example.inventaryapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.inventaryapp.model.Usuarios
import kotlinx.coroutines.flow.Flow

@Dao
interface usuariosDao {

    @Query("SELECT * FROM USUARIOS")
    fun getUsuarios(): Flow<List<Usuarios>>

    @Query("SELECT * FROM USUARIOS WHERE id_usuario = :id")
    fun getUsuariosById(id:Long): Flow<Usuarios>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Usuarios)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(usuario: Usuarios)

    @Delete
    suspend fun delete(usuario: Usuarios)

}