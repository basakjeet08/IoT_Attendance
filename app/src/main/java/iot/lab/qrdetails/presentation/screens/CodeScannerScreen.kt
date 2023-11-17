package iot.lab.qrdetails.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import iot.lab.qrdetails.presentation.states.ScannerStates


@Composable
fun CodeScannerScreen(
    scannerState: ScannerStates,
    onStartScannerClick: () -> Unit,
    finishScannerState: () -> Unit,
    navigateOnSuccess: () -> Unit
) {

    // Checking the Scanner State and prompting the user accordingly
    when (scannerState) {
        is ScannerStates.Idle -> {

            // Starting the Code Scanner
            onStartScannerClick()
        }

        is ScannerStates.Running -> {

            // The Code Scanner is Running
            Toast.makeText(LocalContext.current, "Running", Toast.LENGTH_SHORT).show()
        }

        is ScannerStates.Success -> {
            Toast.makeText(
                LocalContext.current,
                "Roll Number : ${scannerState.rollNumber}",
                Toast.LENGTH_SHORT
            ).show()
            finishScannerState()
            navigateOnSuccess()
        }

        is ScannerStates.Cancelled -> {
            Toast.makeText(LocalContext.current, "Scanning Cancelled", Toast.LENGTH_SHORT).show()
            finishScannerState()
        }

        is ScannerStates.Failure -> {

            Toast.makeText(LocalContext.current, scannerState.errorMessage, Toast.LENGTH_SHORT)
                .show()

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = onStartScannerClick) {
                    Text(text = "Try Again ?")
                }
            }
            finishScannerState()
        }

        is ScannerStates.Complete -> {

        }
    }
}