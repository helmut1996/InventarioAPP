package com.example.inventaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.inventaryapp.navigation.NavManager
import com.example.inventaryapp.ui.theme.InventaryAppTheme
import com.example.inventaryapp.viewmodel.viewModelCategory
import com.example.inventaryapp.viewmodel.viewModelHome
import com.example.inventaryapp.viewmodel.viewModelProduct
import com.example.inventaryapp.viewmodel.viewmodelUsers
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val categoryVM:viewModelCategory by viewModels()
        val userVM: viewmodelUsers by viewModels()
        val productVM: viewModelProduct by viewModels()
        val homeVM:viewModelHome by viewModels()
        setContent {
            InventaryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(categoryVM,userVM,productVM, homeVM)
                }
            }
        }
    }
}


