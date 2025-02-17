package com.example.holidaylibrary.data.remote.model

import com.example.holidaylibrary.util.Util

sealed class ResultCombine {
    data class Success(val numberOfApiSuccess: Int) : ResultCombine()
    data class Error(val message: String?) : ResultCombine()
}

enum class Source(val url: String){
    HolidayApi(Util.BASE_URL_HOLIDAY_API),
    Calendarific(Util.BASE_URL_CALENDARIFIC),
    AbstractApi(Util.BASE_URL_ABSTRACT_API)
}
