package com.example.holidaylibrary.data.repository

import com.example.holidaylibrary.data.remote.abstractapi.AbstractApiService
import com.example.holidaylibrary.data.remote.abstractapi.model.ResponseAbstractApi
import com.example.holidaylibrary.data.remote.calendarific.CalendarificService
import com.example.holidaylibrary.data.remote.calendarific.model.calendarific.ResponseCalendarific
import com.example.holidaylibrary.data.remote.holidayapi.HolidayApiService
import com.example.holidaylibrary.data.remote.holidayapi.model.HolidayApiResponse
import com.example.holidaylibrary.data.remote.model.ResultCombine
import com.example.holidaylibrary.data.remote.model.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

interface HolidayCheckerRepository {
    suspend fun checkHoliday(
        countryCode: String,
        day: Int,
        month: Int,
        year: Int
    ): Flow<ResultCombine>
}

class HolidayCheckerRepositoryImpl(
    private val abstractApiService: AbstractApiService,
    private val holidayApiService: HolidayApiService,
    private val calendarificService: CalendarificService
) : HolidayCheckerRepository {
    override suspend fun checkHoliday(
        countryCode: String,
        day: Int,
        month: Int,
        year: Int
    ): Flow<ResultCombine> {
        val holiday1 = flow {
            emit(abstractApiService.getHoliday(countryCode, year, month, day))
        }.catch {
            emit(
                listOf(ResponseAbstractApi().apply { throwable = it })
            )
        }
        val holiday2 =
            flow { emit(holidayApiService.getHoliday(countryCode, year, month, day)) }.catch {
                emit(
                    HolidayApiResponse().apply { throwable = it }
                )
            }
        val holiday3 =
            flow { emit(calendarificService.getHoliday(countryCode, year, month, day)) }.catch {
                emit(
                    ResponseCalendarific().apply { throwable = it }
                )
            }
        return flow {
            combine(holiday1, holiday2, holiday3) { res1, res2, res3 ->
                var numberOfApiSuccess = 0
                var error = ""
                //response 1
                val error1 = res1.isEmpty() || res1.firstOrNull()?.throwable != null
                if (error1) {
                    error += "${Source.AbstractApi.url} ${res1.firstOrNull()?.throwable?.toString() ?: EMPTY_MESSAGE}"
                } else {
                    numberOfApiSuccess++
                }
                //response 2
                val error2 = res2.status != 200 || res2.holidays.isEmpty() || res2.throwable != null
                if (error2) {
                    error += "\n${Source.HolidayApi.url} ${res2.throwable?.toString() ?: EMPTY_MESSAGE}"
                } else {
                    numberOfApiSuccess++
                }
                //response 3
                val error3 =
                    res3.meta?.code != 200 || res3.response?.holidays?.isEmpty() == true || res3.throwable != null
                if (error3) {
                    error += "\n${Source.Calendarific.url} ${res3.throwable?.toString() ?: EMPTY_MESSAGE}"
                } else {
                    numberOfApiSuccess++
                }
                if (error1 && error2 && error3) {
                    emit(ResultCombine.Error(error))
                    return@combine
                }
                emit(ResultCombine.Success(numberOfApiSuccess))
            }.collect()
        }
    }
}

const val EMPTY_MESSAGE = "The api response was empty"
