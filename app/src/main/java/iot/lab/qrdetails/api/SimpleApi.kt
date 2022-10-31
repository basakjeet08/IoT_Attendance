package iot.lab.qrdetails.api

import iot.lab.qrdetails.model.Data
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {
    @GET("attendance")
    suspend fun getPost(@Query("filter[roll][_eq]") number: String): retrofit2.Response<Data>

    @GET("attendance")
    suspend fun getPostOfFixedDay(
        @Query("filter[roll][_eq]") number: String,
        @Query("filter[day(in_time)][_eq]") inTimeDay: String,
        @Query("filter[month(in_time)][_eq]") inTimeMonth: String,
        @Query("filter[year(in_time)][_eq]") inTimeYear: String
    ): retrofit2.Response<Data>


    @GET("attendance")
    suspend fun getPostBetweenDays(
        @Query("filter[roll][_eq]") number: String,
        @Query("filter[in_time][_between]") inTimeBetween: String
    ): retrofit2.Response<Data>

//    @GET("registration")
//    suspend fun getRegistrationDetails(
//        @Query("filter[roll][_eq]") number: String ,
//    ): retrofit2.Response<>
//    


//    @GET("posts/{postNumber}")
//    suspend fun getPost2(
//        @Path("postNumber") number: Int
//    ): retrofit2.Response<Post>
} 