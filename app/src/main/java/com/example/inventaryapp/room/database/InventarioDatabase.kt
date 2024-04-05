package com.example.inventaryapp.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inventaryapp.model.Productos
import com.example.inventaryapp.model.Usuarios
import com.example.inventaryapp.model.categoria
import com.example.inventaryapp.room.dao.CategoriaDao
import com.example.inventaryapp.room.dao.ProductosDao
import com.example.inventaryapp.room.dao.usuariosDao

@Database(entities = [Usuarios::class, categoria::class, Productos::class], version = 1, exportSchema = false)
abstract class InventarioDatabase:RoomDatabase() {
    abstract fun usuarioDao(): usuariosDao
    abstract fun productoDao():ProductosDao
    abstract fun categoriaDao():CategoriaDao
}