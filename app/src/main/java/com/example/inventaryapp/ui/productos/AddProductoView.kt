package com.example.inventaryapp.ui.productos

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.example.inventaryapp.R
import com.example.inventaryapp.components.Alert
import com.example.inventaryapp.model.Productos
import com.example.inventaryapp.util.Constants
import com.example.inventaryapp.viewmodel.viewModelCategory
import com.example.inventaryapp.viewmodel.viewModelProduct
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductoView(
    navController: NavController,
    productoVM: viewModelProduct,
    categoryVM: viewModelCategory
) {
    var nombre by remember { mutableStateOf("") }
    var precios by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }

    //pickImage
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri

        }

    //spinner
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    val listcategory by categoryVM.cronosllist.collectAsState()


    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Nuevo Producto") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                },
            )
        }
    ) { pad ->
        Column(
            modifier = Modifier
                .padding(pad)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text(text = "Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
            )

            OutlinedTextField(
                value = precios,
                onValueChange = { precios = it },
                label = { Text(text = "Precio") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            OutlinedTextField(
                value = stock,
                onValueChange = { stock = it },
                label = { Text(text = "Cantidad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
            )

            Column(Modifier.padding(10.dp)) {
                OutlinedTextField(
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textfieldSize = coordinates.size.toSize()
                        },
                    readOnly = true,
                    label = {Text("Categorias")},
                    trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                ) {
                    listcategory.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedText = label.nombre
                            expanded = false
                        }, text = {Text(text = label.nombre)})
                    }
                }
            }



            //imagen
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {


                imageUri?.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        bitmap.value = MediaStore.Images
                            .Media.getBitmap(context.contentResolver, it)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, it)
                        bitmap.value = ImageDecoder.decodeBitmap(source)
                    }

                    bitmap.value?.let { btm ->
                        Image(
                            bitmap = btm.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .padding(20.dp)
                        )
                    }
                }
                IconButton(onClick = {
                    launcher.launch("image/*")
                }) {
                    Icon(painter = painterResource(id = R.drawable.ic_image), contentDescription = null)      
                }
                
            }

            Button(
                onClick = {
                    // Verificar que la imagen y su URI no sean nulos
                    if (imageUri != null && bitmap.value != null && nombre.isNotEmpty() && precios.isNotEmpty() && selectedText.isNotEmpty() && stock.isNotEmpty()) {
                        saveImageToFolder(bitmap.value!!,nombre)
                        productoVM.addProduct(
                            product = Productos(
                                nombre = nombre,
                                precios = precios.toDouble(),
                                categoria = selectedText,
                                stock = stock.toInt(),
                                foto = Constants.urlFoto
                            )
                        )
                        navController.popBackStack()
                    }else {
                        productoVM.showAlert = true
                    }
                },
                modifier = Modifier.padding(top = 20.dp)
            ) {
              
                Text(text = "Guardar")
            }

        }
        if (productoVM.showAlert) {
            Alert(
                title = "Alerta",
                message = "Algunos de los campos esta vacio todos son obligatorios",
                confirmText = "Aceptar",
                onConfirmClick = { productoVM.closeAlert() }) {

            }
        }
    }
}

fun saveImageToFolder(bitmap: Bitmap,nombre:String) {
    val folderName = "ImagenesProducto" // Nombre de la carpeta
    val folder = File(Environment.getExternalStorageDirectory(), folderName)

    if (!folder.exists()) {
        folder.mkdirs()
    }

    val fileName = "${nombre}_${System.currentTimeMillis()}.jpg" // Nombre del archivo

    val file = File(folder, fileName)

    try {
        val outputStream = FileOutputStream(file)
        Constants.urlFoto = file.absolutePath
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        Log.d("SaveImageExample", "Imagen guardada en: ${file.absolutePath}")
    } catch (e: IOException) {
        Log.e("SaveImageExample", "Error al guardar la imagen: ${e.message}")
    }
}