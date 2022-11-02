package iot.lab.qrdetails.repository

import iot.lab.qrdetails.api.RetrofitInstance
import iot.lab.qrdetails.model.Data
import iot.lab.qrdetails.model.EventData
import retrofit2.Response

class Repository {

    //This fetches the attendance of roll without filters
    suspend fun getPostByRoll(number : String) : Response<Data> {
        return RetrofitInstance.api.getPostByRoll(number)
    }

    //This fetches the attendance of roll of a fixed Date
    suspend fun getPostOfFixedDay(rollNumber : String ,inTimeDay : String , inTimeMonth : String, inTimeYear : String) : Response<Data>{
        return RetrofitInstance.api.getPostOfFixedDay(rollNumber , inTimeDay , inTimeMonth , inTimeYear)
    }

    //This fetches the attendance of roll in a given range of Date
    suspend fun getPostBetweenDays(rollNumber : String , inTimeBetween : String) : Response<Data>{
        return RetrofitInstance.api.getPostBetweenDays(rollNumber , inTimeBetween)
    }

    // This fetches the registration details of a given Roll
    suspend fun getRegistrationDetails(rollNumber : String) : Response<EventData>{
        return RetrofitInstance.apiEvent.getRegistrationDetails(rollNumber)
    }

}