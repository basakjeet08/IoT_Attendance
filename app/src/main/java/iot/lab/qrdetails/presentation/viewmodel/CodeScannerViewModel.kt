package iot.lab.qrdetails.presentation.viewmodel

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iot.lab.qrdetails.core.scanner.CustomBarCodeScanner
import iot.lab.qrdetails.data.model.EventData
import iot.lab.qrdetails.data.repository.Repository
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
     * This function starts the scanner using the [scanner] variable of the [CustomBarCodeScanner]
     * class.
     *
     * It also passes the success, cancelled and Failure listeners and code which should be executed
     * when each of them occurs
     */
    fun startScanner() {

        // Starting the scanner
        scanner?.startScanner(

            // Success Listener
            onSuccess = {
                onCodeSuccess(it)
            },

            // Cancelled Listener
            onCancelled = {},

            // failure Listener
            onFailure = {}
        )
    }


    /**
     * This function is invoked when we find the roll number of a student from the bar code scanner
     */
    private fun onCodeSuccess(code: String) {

        d("View Model", code)
//        getRegistrationDetails(code)
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