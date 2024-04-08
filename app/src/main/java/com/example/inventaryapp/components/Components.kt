package com.example.inventaryapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


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


