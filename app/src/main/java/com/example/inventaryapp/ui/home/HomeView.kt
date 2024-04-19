@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.inventaryapp.ui.home

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.inventaryapp.R
import com.example.inventaryapp.components.Alert
import com.example.inventaryapp.viewmodel.viewModelHome
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(homeVM: viewModelHome) {

    val multiplePermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
        )
    )

    LaunchedEffect(Unit) {
        multiplePermissionsState.launchMultiplePermissionRequest()
        if (multiplePermissionsState.allPermissionsGranted) {
            // If all permissions are granted, then show screen with the feature enabled
            getTextToShowGivenPermissions(

                multiplePermissionsState.revokedPermissions,
                multiplePermissionsState.shouldShowRationale
            )

        } else {
            homeVM.showAlert = true

        }

    }
    val animatione  by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.homepages))



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Inicio") },

            )
        }
    ) { pad ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(pad),
                verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Bienbenidos",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            LottieAnimation(
                modifier = Modifier.size(400.dp),
                composition = animatione,
                isPlaying = true
            )
        }
    }



    if (homeVM.showAlert) {
        Alert(
            title = "Alerta",
            message = getTextToShowGivenPermissions(

                multiplePermissionsState.revokedPermissions,
                multiplePermissionsState.shouldShowRationale
            ),
            confirmText = "Aceptar",
            onConfirmClick = { homeVM.closeAlert() }) {

        }
    }
}




private fun getTextToShowGivenPermissions(
    permissions: List<PermissionState>,
    shouldShowRationale: Boolean
): String {
    val revokedPermissionsSize = permissions.size
    if (revokedPermissionsSize == 0) return ""

    val textToShow = StringBuilder().apply {
        append("The ")
    }

    for (i in permissions.indices) {
        textToShow.append(permissions[i].permission)
        when {
            revokedPermissionsSize > 1 && i == revokedPermissionsSize - 2 -> {
                textToShow.append(", and ")
            }
            i == revokedPermissionsSize - 1 -> {
                textToShow.append(" ")
            }
            else -> {
                textToShow.append(", ")
            }
        }
    }
    textToShow.append(if (revokedPermissionsSize == 1) "permission is" else "permissions are")
    textToShow.append(
        if (shouldShowRationale) {
            " important. Please grant all of them for the app to function properly."
        } else {
            " denied. The app cannot function without them."
        }
    )
    return textToShow.toString()
}