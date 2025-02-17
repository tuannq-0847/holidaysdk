package com.example.holidaylibrary.wrapper

import com.example.holidaylibrary.data.remote.abstractapi.AbstractApiService
import com.example.holidaylibrary.data.remote.calendarific.CalendarificService
import com.example.holidaylibrary.data.remote.holidayapi.HolidayApiService
import com.example.holidaylibrary.util.NetworkUtil
import com.example.holidaylibrary.util.Util

object ApiServiceWrapper {

    //calendarific
    val calendarificSerivice = NetworkUtil.createRetrofitClient(
        CalendarificService::class.java,
        NetworkUtil.setupRetrofit(
            Util.BASE_URL_CALENDARIFIC,
            NetworkUtil.createOkHttpClient("api_key" to Util.API_KEY_CALENDARIFIC)
        )
    )

    //abstract api
    val abstractApiService = NetworkUtil.createRetrofitClient(
        AbstractApiService::class.java,
        NetworkUtil.setupRetrofit(
            Util.BASE_URL_ABSTRACT_API,
            NetworkUtil.createOkHttpClient("api_key" to Util.API_KEY_ABSTRACT_API)
        )
    )

    //holiday api
    val holidayApi = NetworkUtil.createRetrofitClient(
        HolidayApiService::class.java,
        NetworkUtil.setupRetrofit(
            Util.BASE_URL_HOLIDAY_API,
            NetworkUtil.createOkHttpClient("key" to Util.API_KEY_HOLIDAY_API)
        )
    )

}
