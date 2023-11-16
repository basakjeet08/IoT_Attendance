package iot.lab.qrdetails.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import iot.lab.qrdetails.presentation.viewmodel.CodeScannerViewModel

@Composable
fun CodeScannerScreen(viewModel: CodeScannerViewModel) {
    LaunchedEffect(Unit) {
        viewModel.startScanner()
    }
}