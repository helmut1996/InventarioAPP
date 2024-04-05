package com.example.inventaryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CATEGORIAS")
data class categoria (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id_categoria")
    val id:Long =0,
    @ColumnInfo("nombre_categoria")
    val nombre:String="",
    @ColumnInfo("descripcion_categoria")
    val descripcion:String=""
)