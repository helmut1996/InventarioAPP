package com.example.inventaryapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector


data class NavItem(
    val label:String,
    val icon:ImageVector,
    val route:String
)

val listOfNavItems = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Filled.Home,
        route = ViewsScreens.HomeView.name
    ),
    NavItem(
        label = "Usuarios",
        icon = Icons.Filled.Person,
        route = ViewsScreens.UsersView.name
    ),
    NavItem(
        label = "categorias",
        icon = Icons.Filled.List,
        route = ViewsScreens.CategoryView.name
    ),
    NavItem(
        label = "producto",
        icon = Icons.Filled.ShoppingCart,
        route = ViewsScreens.ProductoView.name
    )
)
