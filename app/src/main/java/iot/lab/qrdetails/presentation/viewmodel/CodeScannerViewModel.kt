package iot.lab.qrdetails.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iot.lab.qrdetails.core.scanner.CustomBarCodeScanner
import iot.lab.qrdetails.data.model.EventData
import iot.lab.qrdetails.data.repository.Repository
import iot.lab.qrdetails.presentation.states.ScannerStates
import iot.lab.qrdetails.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CodeScannerViewModel(
    private val repository: Repository = Repository()
) : ViewModel() {

    /**
     * Custom Scanner Variable which would help in scanning the Bar Code
     */
    private var scanner: CustomBarCodeScanner? = null


    /**
     * This function is used to set the Scanner so that we can use it later in the App
     */
    fun setScanner(scanner: CustomBarCodeScanner) {
        this.scanner = scanner
    }


    /**
     * This variable keeps the states of the [scanner] and let the UI know what changes to
     * undergo accordingly
     */
    private val _scannerState = MutableStateFlow<ScannerStates>(ScannerStates.Idle)
    val scannerStates = _scannerState.asStateFlow()


    /**
     * This function starts the scanner using the [scanner] variable of the [CustomBarCodeScanner]
     * class.
     *
     * It also passes the success, cancelled and Failure listeners and code which should be executed
     * when each of them occurs
     */
    fun startScanner() {

        _scannerState.value = ScannerStates.Running

        // Starting the scanner
        scanner?.startScanner(

            // Success Listener
            onSuccess = {
                _scannerState.value = ScannerStates.Success(it)
//                getRegistrationDetails(it)
            },

            // Cancelled Listener
            onCancelled = {
                _scannerState.value = ScannerStates.Cancelled
            },

            // failure Listener
            onFailure = {
                _scannerState.value = ScannerStates.Failure(it.message.toString())
            }
        )
    }

    fun finishScannerState() {
        _scannerState.value = ScannerStates.Complete
    }

    private fun resetScannerState() {
        _scannerState.value = ScannerStates.Idle
    }


    /**
     * This variable is used to keep track of the api state of the api call to fetch the registration
     * details of the respective roll number
     */
    private val _registrationApiState = MutableStateFlow<UiState<EventData>>(UiState.Idle)
    val registrationApiState = _registrationApiState.asStateFlow()


    /**
     * This calls the repository and ask it to fetch Registration Details of roll
     */
    private fun getRegistrationDetails(rollNumber: String) {
        viewModelScope.launch {
            val response = repository.getRegistrationDetails(rollNumber)
            _registrationApiState.value = response
        }
    }
}