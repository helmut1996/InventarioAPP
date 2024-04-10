package com.example.inventaryapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PRODUCTOS")
data class Productos(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("producto_id")
    val id:Long = 0,
   @ColumnInfo("producto_nombre")
    val nombre:String="",
    @ColumnInfo("producto_categoria")
    val categoria:String="",
    @ColumnInfo("producto_precios")
    val precios:Double=0.0,
    @ColumnInfo("producto_stock")
    val stock:Int=0,
    @ColumnInfo("producto_foto")
    val foto:String="",

)
