package iot.lab.qrdetails.repository

import iot.lab.qrdetails.api.RetrofitInstance
import iot.lab.qrdetails.model.Data
import iot.lab.qrdetails.model.Post
import retrofit2.Response

class Repository {
    suspend fun getPost(number : String) : Response<Data> {
        return RetrofitInstance.api.getPost(number)
    }


}