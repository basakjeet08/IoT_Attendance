package iot.lab.qrdetails.repository

import iot.lab.qrdetails.api.RetrofitInstance
import iot.lab.qrdetails.model.EventData
import retrofit2.Response

class Repository {

    // This fetches the registration details of a given Roll
    suspend fun getRegistrationDetails(rollNumber: String): Response<EventData> {
        return RetrofitInstance.apiEvent.getRegistrationDetails(rollNumber)
    }

}