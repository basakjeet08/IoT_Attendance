package iot.lab.qrdetails.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import iot.lab.qrdetails.data.model.EventData
import iot.lab.qrdetails.presentation.components.DotTypingAnimation
import iot.lab.qrdetails.presentation.components.CodeScannerCancelled
import iot.lab.qrdetails.presentation.components.CodeScannerFailure
import iot.lab.qrdetails.presentation.components.EndScreenPopup
import iot.lab.qrdetails.presentation.states.ScannerStates
import iot.lab.qrdetails.presentation.states.UiState


/**
 * This function acts as the base Screen composable for the Code Scanner UI.
 *
 * @param scannerState This variable gives the state of the Code Scanner.
 * @param registrationState This variable gives the state of the registration API call.
 * @param onStartScannerClick This function starts the Scanner.
 * @param getRegistrationDetails This function is used to get the registration details using roll input
 * @param resetToIdleState This function resets the Scanner back to the Idle State
 */
@Composable
fun CodeScannerScreenControl(
    scannerState: ScannerStates,
    registrationState: UiState<EventData>,
    onStartScannerClick: () -> Unit,
    getRegistrationDetails: (String) -> Unit,
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

                    val userData = registrationState.data.data[0]

                    val textToShow =
                        "${userData.first_name} ${userData.last_name} is registered to " +
                                "the ${userData.plan_description}"

                    EndScreenPopup(
                        title = "Thank You ${userData.first_name}",
                        desc = textToShow,
                        currentStatus = "Current Status : ${userData.status}",
                        imageVector = Icons.Default.Done,
                        iconTint = Color.Green,
                        onContinueClick = resetToIdleState
                    )
                }

                is UiState.Failure -> {
                    EndScreenPopup(
                        title = "Oops !!",
                        desc = "User is not Registered",
                        imageVector = Icons.Default.Close,
                        iconTint = Color.Red,
                        onContinueClick = resetToIdleState
                    )
                }
            }
        }

        is ScannerStates.Cancelled -> {
            CodeScannerCancelled(resetToIdleState = resetToIdleState)
        }

        is ScannerStates.Failure -> {
            CodeScannerFailure(
                errorMessage = scannerState.errorMessage,
                onSearchIconClick = getRegistrationDetails,
                resetToIdleState = resetToIdleState
            )
        }

        else -> {
            // Do Nothing
        }
    }
}