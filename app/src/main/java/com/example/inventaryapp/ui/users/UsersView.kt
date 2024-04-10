package com.example.inventaryapp.ui.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.inventaryapp.components.CardItemUsers
import com.example.inventaryapp.navigation.ViewsScreens
import com.example.inventaryapp.viewmodel.viewmodelUsers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersView(navigationController: NavController, usersVM: viewmodelUsers) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Usuarios") },

                actions = {
                    IconButton(onClick = {
                        navigationController.navigate(ViewsScreens.AddUsersView.name)
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
            )
        }
    ) { padd ->
        Column(
            modifier = Modifier.padding(padd),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val categoryList by usersVM.userslist.collectAsState()
            LazyColumn {
                this.items(categoryList) { item ->
                    CardItemUsers(
                        nombre = "${item.nombre} ${item.apellido}",
                        username =item.usuario ,
                        email = item.email,
                        icono = Icons.Default.Person,
                        onEdit = {
                            navigationController.navigate("EditUsersView/${item.id}")
                        }) {
                        usersVM.deleteUsers(item)
                    }

                }

            }
        }
    }
}