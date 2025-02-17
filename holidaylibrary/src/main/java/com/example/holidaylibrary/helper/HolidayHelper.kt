package com.example.holidaylibrary.helper

import com.example.holidaylibrary.data.remote.model.ResultCombine
import com.example.holidaylibrary.data.repository.HolidayCheckerRepository
import com.example.holidaylibrary.data.repository.HolidayCheckerRepositoryImpl
import com.example.holidaylibrary.wrapper.ApiServiceWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object HolidayHelper : HolidayChecker {
    private val repository: HolidayCheckerRepository = HolidayCheckerRepositoryImpl(
        ApiServiceWrapper.abstractApiService,
        ApiServiceWrapper.holidayApi,
        ApiServiceWrapper.calendarificSerivice
    )

    private var state = HolidayState.ANY

    fun setState(state: HolidayState): HolidayHelper {
        this.state = state
        return this
    }

    override fun checkHoliday(
        countryCode: String,
        day: Int,
        month: Int,
        year: Int
    ): Flow<Result<Boolean>> {
        return flow {
            repository.checkHoliday(countryCode, day, month, year).collect {
                if (it is ResultCombine.Success) {
                    emit(Result.success(handleResponseByState(it.numberOfApiSuccess)))
                } else {
                    val errorMsg = (it as ResultCombine.Error).run { message }
                    emit(Result.failure(Throwable(errorMsg)))
                }
                //reset to default state
                setState(HolidayState.ANY)
            }
        }
    }

    private fun handleResponseByState(numberOfApiSuccess: Int): Boolean {
        return when (state) {
            HolidayState.ANY -> {
                numberOfApiSuccess > 0
            }

            HolidayState.ALL -> {
                numberOfApiSuccess == 3
            }

            else -> {
                numberOfApiSuccess > 1
            }
        }

    }
}
