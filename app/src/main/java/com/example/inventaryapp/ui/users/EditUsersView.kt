package com.example.inventaryapp.ui.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inventaryapp.model.Usuarios
import com.example.inventaryapp.viewmodel.viewmodelUsers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUsersView(navController: NavController, usersVM: viewmodelUsers,id:Long){
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }
    val state = usersVM.state

    nombre = state.nombre
    apellido = state.apellido
    username = state.username
    email = state.email
    clave = state.password

    LaunchedEffect(Unit) {
        usersVM.getUserById(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Editar Usuario") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        usersVM.updateUsers(
                            Usuarios(
                                id = id,
                                nombre = nombre,
                                apellido = apellido,
                                usuario = username,
                                email = email,
                                clave= clave
                            )
                        )
                        navController.popBackStack()

                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                    }
                }
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
            )

            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text(text = "Apellido") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            OutlinedTextField(
                value = clave,
                onValueChange = { clave = it },
                label = { Text(text = "Clave") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )
        }

    }
}