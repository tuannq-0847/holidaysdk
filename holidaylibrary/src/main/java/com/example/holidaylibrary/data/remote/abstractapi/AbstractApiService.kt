package com.example.holidaylibrary.data.remote.abstractapi

import com.example.holidaylibrary.data.remote.abstractapi.model.ResponseAbstractApi
import retrofit2.http.GET
import retrofit2.http.Query

interface AbstractApiService {
    @GET("v1/")
    suspend fun getHoliday(
        @Query("country")
        countryCode: String,
        @Query("year")
        year: Int,
        @Query("month")
        month: Int,
        @Query("day")
        day: Int
    ): List<ResponseAbstractApi>
}
