package iot.lab.qrdetails.presentation.states

sealed interface ScannerStates {
    object Idle : ScannerStates
    object Running : ScannerStates
    object Success : ScannerStates
    object Cancelled : ScannerStates
    class Failure(val errorMessage: String) : ScannerStates
}