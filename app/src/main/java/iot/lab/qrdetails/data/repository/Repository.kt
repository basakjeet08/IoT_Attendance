package iot.lab.qrdetails.data.repository

import iot.lab.qrdetails.data.remote.RetrofitInstance
import iot.lab.qrdetails.data.model.EventData
import retrofit2.Response

class Repository {

    // This fetches the registration details of a given Roll
    suspend fun getRegistrationDetails(rollNumber: String): Response<EventData> {
        return RetrofitInstance.apiEvent.getRegistrationDetails(rollNumber)
    }

}