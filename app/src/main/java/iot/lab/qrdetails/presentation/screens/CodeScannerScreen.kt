package iot.lab.qrdetails.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import iot.lab.qrdetails.core.scanner.CustomBarCodeScanner

@Composable
fun CodeScannerScreen() {

    val scanner = CustomBarCodeScanner(LocalContext.current)
    scanner.startScanner(
        onSuccess = {},
        onCancelled = {},
        onFailure = {}
    )

}