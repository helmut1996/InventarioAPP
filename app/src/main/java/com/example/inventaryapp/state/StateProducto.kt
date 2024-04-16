package com.example.inventaryapp.state

data class StateProducto (
    val id:Long = 0,
    val nombre:String="",
    val categoria:String="",
    val precios:Double=0.0,
    val stock:Int=0,
    val foto:String="",
)