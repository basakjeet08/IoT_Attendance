package iot.lab.qrdetails.api


import iot.lab.qrdetails.util.Constants
import iot.lab.qrdetails.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Contains the Base Url which gives the attendance details
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }

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