package iot.lab.qrdetails.repository

import iot.lab.qrdetails.api.RetrofitInstance
import iot.lab.qrdetails.model.Data
import iot.lab.qrdetails.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(number : String) : Response<Data> {
        return RetrofitInstance.api.getPost(number)
    }

    //Function added by Anirban Basak
    suspend fun getPostOfFixedDay(inTimeDay : String , inTimeMonth : String, inTimeYear : String) : Response<Data>{
        return RetrofitInstance.api.getPostOfFixedDay(inTimeDay , inTimeMonth , inTimeYear)
    }

    //Function added by Anirban Basak
    suspend fun getPostBetweenDays(inTimeBetween : String) : retrofit2.Response<Data>{
        return RetrofitInstance.api.getPostBetweenDays(inTimeBetween)
    }

}