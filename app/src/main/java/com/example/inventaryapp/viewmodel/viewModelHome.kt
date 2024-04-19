package com.example.inventaryapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class viewModelHome:ViewModel() {
var showAlert by mutableStateOf(false)


fun closeAlert(){showAlert = false}



}