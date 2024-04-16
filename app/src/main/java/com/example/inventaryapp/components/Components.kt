package com.example.inventaryapp.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.inventaryapp.viewmodel.viewModelProduct


@Composable
fun CardItem(
    title: String,
    desciption: String,
    icono: ImageVector,
    onEdit: () -> Unit,
    Ondelete: () -> Unit,
){
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(150.dp)
            .clickable { onEdit() }
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(15.dp)
        ) {
        Icon(imageVector = icono,
            contentDescription = "",
            modifier = Modifier
                .size(45.dp)
                .padding(end = 5.dp)
        )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = title)
                Text(text = desciption)
                IconButton(onClick = { Ondelete() },
                        modifier = Modifier.size(30.dp) ) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription =null,
                        tint = Color.Red)
                }
            }

        }
    }
}



@Composable
fun CardItemUsers(
    nombre: String,
    username: String,
    email: String,
    icono: ImageVector,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
){
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(200.dp)
            .clickable { onEdit() }
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Icon(imageVector = icono,
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 5.dp)
            )
                Text(text ="Nombre: $nombre")
                Text(text ="Usuario: $username")
                Text(text = "Correo: $email")
                Button(onClick = { onDelete() }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )) {
                    Text(text = "Eliminar")
                }
               
                }
            

        }
    }


@Composable
fun Alert(
    title: String,
    message: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {
    val scroll = rememberScrollState(0)

    AlertDialog(
        onDismissRequest = { onDismissClick() },
        title = { Text(text = title) },
        text = {
            Text(
                text = message,
                textAlign = TextAlign.Justify,
                modifier = Modifier.verticalScroll(scroll)
            )
        },
        confirmButton = {
            Button(onClick = { onConfirmClick() }) {
                Text(text = confirmText)
            }
        },
    )


}

@Composable
fun SpinnerCategoria(productVM:viewModelProduct) {





}