package iot.lab.qrdetails.api

import iot.lab.qrdetails.model.Data
import iot.lab.qrdetails.model.EventData
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    // For fetching attendance with only Roll Number
    @GET("attendance")
    suspend fun getPostByRoll(@Query("filter[roll][_eq]") number: String): retrofit2.Response<Data>

    // For fetching attendance with Roll number and of a particular date
    @GET("attendance")
    suspend fun getPostOfFixedDay(
        @Query("filter[roll][_eq]") number: String,
        @Query("filter[day(in_time)][_eq]") inTimeDay: String,
        @Query("filter[month(in_time)][_eq]") inTimeMonth: String,
        @Query("filter[year(in_time)][_eq]") inTimeYear: String
    ): retrofit2.Response<Data>

    // For fetching attendance of a roll Number between two given dates
    @GET("attendance")
    suspend fun getPostBetweenDays(
        @Query("filter[roll][_eq]") number: String,
        @Query("filter[in_time][_between]") inTimeBetween: String
    ): retrofit2.Response<Data>


    // For fetching the registration details of a single roll Number
    @GET("innovance_registration")
    suspend fun getRegistrationDetails(
        @Query("filter[id][_eq]") number: String
    ): retrofit2.Response<EventData>


//    @GET("posts/{postNumber}")
//    suspend fun getPost2(
//        @Path("postNumber") number: Int
//    ): retrofit2.Response<Post>
} 