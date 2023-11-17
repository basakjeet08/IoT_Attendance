package iot.lab.qrdetails.presentation.states

sealed interface ScannerStates {
    object Idle : ScannerStates
    object Running : ScannerStates
    class Success(val rollNumber: String) : ScannerStates
    object Cancelled : ScannerStates
    class Failure(val errorMessage: String) : ScannerStates
    object Complete : ScannerStates
}