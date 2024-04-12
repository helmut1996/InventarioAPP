@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.inventaryapp.ui.home

import android.Manifest
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.inventaryapp.components.Alert
import com.example.inventaryapp.viewmodel.viewModelHome
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState



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


        } else {
            homeVM.showAlert = true

        }

    }

    Text(text = "Home View")



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