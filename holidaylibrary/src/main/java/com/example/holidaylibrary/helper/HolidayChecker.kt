package com.example.holidaylibrary.helper

import kotlinx.coroutines.flow.Flow

interface HolidayChecker {

    fun checkHoliday(countryCode: String, day: Int, month: Int, year: Int): Flow<Result<Boolean>>
}
