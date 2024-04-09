package com.example.inventaryapp.repository

import com.example.inventaryapp.model.Usuarios
import com.example.inventaryapp.room.dao.usuariosDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class repositoryUsers @Inject constructor(private val userDao: usuariosDao) {


    suspend fun addUsers(users: Usuarios) = userDao.insert(users)
    suspend fun updateUsers(users: Usuarios) = userDao.update(users)
    suspend fun deleteUser(users: Usuarios) = userDao.delete(users)
    fun getAllUsers(): Flow<List<Usuarios>> = userDao.getUsuarios().flowOn(Dispatchers.IO).conflate()
    fun getUserById(id:Long): Flow<Usuarios> = userDao.getUsuariosById(id).flowOn(
        Dispatchers.IO).conflate()
}