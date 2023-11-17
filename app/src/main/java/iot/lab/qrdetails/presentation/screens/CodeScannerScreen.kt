package iot.lab.qrdetails.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import iot.lab.qrdetails.data.model.EventData
import iot.lab.qrdetails.presentation.components.DotTypingAnimation
import iot.lab.qrdetails.presentation.components.CodeScannerCancelled
import iot.lab.qrdetails.presentation.components.CodeScannerFailure
import iot.lab.qrdetails.presentation.states.ScannerStates
import iot.lab.qrdetails.presentation.states.UiState


@Composable
fun CodeScannerScreenControl(
    scannerState: ScannerStates,
    onStartScannerClick: () -> Unit,
    registrationState: UiState<EventData>,
    resetToIdleState: () -> Unit
) {

    // Checking the Scanner State and prompting the user accordingly
    when (scannerState) {
        is ScannerStates.Idle -> {

            // Starting the Code Scanner
            onStartScannerClick()
        }

        is ScannerStates.Success -> {
            when (registrationState) {
                is UiState.Idle -> {
                    // Do Nothing
                }

                is UiState.Loading -> {
                    DotTypingAnimation(modifier = Modifier.fillMaxSize())
                }

                is UiState.Success -> {

                }

                is UiState.Failure -> {

                }
            }
        }

        is ScannerStates.Cancelled -> {
            CodeScannerCancelled(resetToIdleState = resetToIdleState)
        }

        is ScannerStates.Failure -> {
            CodeScannerFailure(
                scannerState.errorMessage,
                resetToIdleState = resetToIdleState
            )
        }

        else -> {
            // Do Nothing
        }
    }
}