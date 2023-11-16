package iot.lab.qrdetails.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import iot.lab.qrdetails.util.CustomBarCodeScanner

@Composable
fun CodeScannerScreen() {

    val scanner = CustomBarCodeScanner(LocalContext.current)
    scanner.startScanner(
        onSuccess = {},
        onCancelled = {},
        onFailure = {}
    )

}