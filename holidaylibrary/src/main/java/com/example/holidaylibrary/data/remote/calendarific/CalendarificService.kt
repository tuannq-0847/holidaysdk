package com.example.holidaylibrary.data.remote.calendarific

import com.example.holidaylibrary.data.remote.calendarific.model.calendarific.ResponseCalendarific
import retrofit2.http.GET
import retrofit2.http.Query

interface CalendarificService {
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
    ): ResponseCalendarific
}
