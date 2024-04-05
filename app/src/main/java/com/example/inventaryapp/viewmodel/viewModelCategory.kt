package com.example.inventaryapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCronos().collect{item->
                if (item.isNullOrEmpty()){
                    _categoryList.value = emptyList()
                }else{
                    _categoryList.value = item
                }
            }
        }
    }

    fun addCategory(category:categoria) = viewModelScope.launch { repository.addCategory(category) }
    fun updateCategory(category:categoria) = viewModelScope.launch { repository.updateCategory(category) }
    fun deleteCategory(category:categoria) = viewModelScope.launch { repository.deleteCategory(category) }

}