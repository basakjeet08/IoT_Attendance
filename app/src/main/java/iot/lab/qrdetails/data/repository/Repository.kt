package iot.lab.qrdetails.data.repository

import iot.lab.qrdetails.data.remote.RetrofitInstance
import iot.lab.qrdetails.data.model.EventData
import iot.lab.qrdetails.presentation.states.UiState


class Repository {

    // This fetches the registration details of a given Roll
    suspend fun getRegistrationDetails(rollNumber: String): UiState<EventData> {

        // Response from the Server
        val response = RetrofitInstance.apiEvent.getRegistrationDetails(rollNumber)

        // Checking if the data is valid or not
        return if (response.isSuccessful)
            UiState.Success(response.body()!!)
        else
            UiState.Failure(errorMessage = "Error Connecting to the Server")
    }
}