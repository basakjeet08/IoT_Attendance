package iot.lab.qrdetails.api


import iot.lab.qrdetails.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Contains the Event URL which gives the url of the event and registration details
    private val retrofitEvent by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_EVENT_INNOVANCE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiEvent: SimpleApi by lazy {
        retrofitEvent.create(SimpleApi::class.java)
    }

}