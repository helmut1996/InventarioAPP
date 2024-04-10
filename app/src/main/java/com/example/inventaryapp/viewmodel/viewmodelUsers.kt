package com.example.inventaryapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventaryapp.model.Usuarios
import com.example.inventaryapp.repository.repositoryUsers
import com.example.inventaryapp.state.StateUsuarios
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class viewmodelUsers @Inject constructor(private val repository:repositoryUsers):ViewModel() {
    private val _usersList = MutableStateFlow<List<Usuarios>>(emptyList())
    val userslist = _usersList.asStateFlow()

    var state by mutableStateOf(StateUsuarios())
        private set

    var showAlert by mutableStateOf(false)
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllUsers().collect{item->
                if (item.isEmpty()){
                    _usersList.value = emptyList()
                }else{
                    _usersList.value = item
                }
            }
        }
    }

    fun getUserById(id:Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserById(id).collect {item ->
                state = state.copy(
                    nombre = item.nombre,
                    apellido = item.apellido,
                    username = item.usuario,
                    email =  item.email,
                    password = item.clave

                )
            }
        }
    }

    fun addUsers(users: Usuarios) = viewModelScope.launch { repository.addUsers(users) }
    fun updateUsers(users: Usuarios) = viewModelScope.launch { repository.updateUsers(users) }
    fun deleteUsers(users: Usuarios) = viewModelScope.launch { repository.deleteUser(users) }

    fun closeAlert(){ showAlert = false }
}