package iot.lab.qrdetails.data.remote

import iot.lab.qrdetails.data.model.EventData
import iot.lab.qrdetails.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    // For fetching the registration details of a single roll Number
    @GET(Constants.REGISTRATION_ENDPOINT)
    suspend fun getRegistrationDetails(
        @Query("filter[id][_eq]") number: String
    ): retrofit2.Response<EventData>

}