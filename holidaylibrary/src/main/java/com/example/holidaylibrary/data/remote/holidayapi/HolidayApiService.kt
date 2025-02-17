package com.example.holidaylibrary.data.remote.holidayapi

import com.example.holidaylibrary.data.remote.holidayapi.model.HolidayApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HolidayApiService {

    @GET("holidays")
    suspend fun getHoliday(
        @Query("country")
        countryCode: String,
        @Query("year")
        year: Int,
        @Query("month")
        month: Int,
        @Query("day")
        day: Int
    ): HolidayApiResponse
}