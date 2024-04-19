package com.example.inventaryapp.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.inventaryapp.R

@Composable
fun CardItem(
    title: String,
    desciption: String,
    icono: ImageVector,
    onEdit: () -> Unit,
    Ondelete: () -> Unit,
) {
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
            Icon(
                imageVector = icono,
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
                IconButton(
                    onClick = { Ondelete() },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
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
) {
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
            Icon(
                imageVector = icono,
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 5.dp)
            )
            Text(text = "Nombre: $nombre")
            Text(text = "Usuario: $username")
            Text(text = "Correo: $email")
            Button(
                onClick = { onDelete() }, colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Eliminar")
            }

        }


    }
}

@Composable
fun CardProduct(
    nombre: String,
    categoria: String,
    cantidad: String,
    precio: String,
    foto: String,
    onDetails: () -> Unit,
    onDelete: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .height(280.dp)
                .width(250.dp)
                .clickable { onDetails() }
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxSize()
            ) {

                AsyncImage(
                    model = foto,
                    placeholder = painterResource(id = R.drawable.ic_image),
                    error = painterResource(id = R.drawable.errorphoto),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1280f / 692f)
                )

                Text(text = nombre)
                Text(text = "Categoria: $categoria")
                Text(text = "Precio: $precio")
                Text(text = "Stock: $cantidad")
                Button(
                    onClick = { onDelete() }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Eliminar")
                }

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
