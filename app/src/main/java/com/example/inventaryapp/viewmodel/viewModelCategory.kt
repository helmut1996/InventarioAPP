package com.example.inventaryapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventaryapp.state.StateCategory
import com.example.inventaryapp.model.categoria
import com.example.inventaryapp.repository.repositoryCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class viewModelCategory @Inject constructor(private val repository:repositoryCategory):ViewModel() {
    private val _categoryList = MutableStateFlow<List<categoria>>(emptyList())
    val cronosllist = _categoryList.asStateFlow()

    var state by mutableStateOf(StateCategory())
        private set
    var showAlert by mutableStateOf(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCronos().collect{item->
                if (item.isEmpty()){
                    _categoryList.value = emptyList()
                }else{
                    _categoryList.value = item
                }
            }
        }
    }

    fun getCategoryById(id:Long){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCategoryById(id).collect {item ->
                state = state.copy(
                    nombre = item.nombre,
                    descripcion = item.descripcion
                )
        }
        }
    }

    fun addCategory(category:categoria) = viewModelScope.launch { repository.addCategory(category) }
    fun updateCategory(category:categoria) = viewModelScope.launch { repository.updateCategory(category) }
    fun deleteCategory(category:categoria) = viewModelScope.launch { repository.deleteCategory(category) }

    fun closeAlert() { showAlert = false}
}