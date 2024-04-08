package com.example.inventaryapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.inventaryapp.ui.categoria.AddCategoryView
import com.example.inventaryapp.ui.categoria.CategoryView
import com.example.inventaryapp.ui.categoria.EditCategoryView
import com.example.inventaryapp.ui.home.HomeView
import com.example.inventaryapp.ui.productos.ProductView
import com.example.inventaryapp.ui.users.AddUsersView
import com.example.inventaryapp.ui.users.UsersView
import com.example.inventaryapp.viewmodel.viewModelCategory

@Composable
fun NavManager(categoryVM:viewModelCategory) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {

            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach { navItem ->
                    NavigationBarItem(selected =currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                                  navController.navigate(navItem.route){
                                      popUpTo(navController.graph.findStartDestination().id){
                                          saveState = true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }
                        },
                        icon = {
                               Icon(imageVector = navItem.icon, contentDescription =null )
                        },
                        label = {
                               Text(text = navItem.label)
                        }
                    )

                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ViewsScreens.HomeView.name,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            composable(route = ViewsScreens.HomeView.name) {
                HomeView()
            }
            composable(route = ViewsScreens.UsersView.name) {
                UsersView(navController)
            }
            composable(route = ViewsScreens.CategoryView.name) {
                CategoryView(navController,categoryVM)
            }
            composable(route = ViewsScreens.ProductoView.name) {
                ProductView()
            }

            composable(route = ViewsScreens.AddUsersView.name) {
                AddUsersView(navController)
            }
            composable(route = ViewsScreens.AddCategoriaView.name) {
                AddCategoryView(navController,categoryVM)
            }
            composable(route = "EditCategoryView/{id}", arguments = listOf(
                navArgument("id"){type= NavType.LongType}
            )) {
                val id = it.arguments?.getLong("id") ?:0
                EditCategoryView(navController,categoryVM, id)
            }

            }

    }
}