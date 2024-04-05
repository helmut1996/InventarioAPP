package com.example.inventaryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USUARIOS")
data class Usuarios(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    val id:Long = 0,
    @ColumnInfo(name = "nombre_usuario")
    val nombre:String ="",
    @ColumnInfo(name = "apellido_usuario")
    val apellido:String="",
    @ColumnInfo(name = "usuario_usuario")
    val usuario:String="",
    @ColumnInfo(name = "usuario_email")
    val email:String="",
    @ColumnInfo(name = "usuario_clave")
    val clave:String=""
)
