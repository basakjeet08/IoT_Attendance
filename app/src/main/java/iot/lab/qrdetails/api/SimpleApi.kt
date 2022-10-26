package iot.lab.qrdetails.api

import iot.lab.qrdetails.model.Data
import iot.lab.qrdetails.model.Post
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {
    @GET("attendance")
    suspend fun getPost(@Query("filter[roll][_eq]") number: String): retrofit2.Response<Data>




//    @GET("posts/{postNumber}")
//    suspend fun getPost2(
//        @Path("postNumber") number: Int
//    ): retrofit2.Response<Post>
} 