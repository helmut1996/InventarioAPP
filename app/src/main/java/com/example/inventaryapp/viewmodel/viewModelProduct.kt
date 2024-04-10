package com.example.inventaryapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.inventaryapp.repository.repositoryCategory
import com.example.inventaryapp.repository.repositoryProducto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class viewModelProduct @Inject constructor(private val repository: repositoryProducto):ViewModel() {
}