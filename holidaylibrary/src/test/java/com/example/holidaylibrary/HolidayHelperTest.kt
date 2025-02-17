package com.example.holidaylibrary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.holidaylibrary.data.remote.model.ResultCombine
import com.example.holidaylibrary.data.repository.HolidayCheckerRepository
import com.example.holidaylibrary.helper.HolidayHelper
import com.example.holidaylibrary.helper.HolidayState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HolidayHelperTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private val repository: HolidayCheckerRepository = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockkObject(HolidayHelper)
        // Mock the repository calls while keeping the original field
        every { HolidayHelper getProperty "repository" } returns repository
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test check holiday return true when 3 api success with state all`() = runTest {
        coEvery { repository.checkHoliday(any(),any(),any(),any()) } returns flow {
            emit(ResultCombine.Success(3))
        }
        HolidayHelper.setState(HolidayState.ALL).checkHoliday("",1,1,2025).collect{
            Assert.assertEquals(it.getOrNull(),true)
        }
    }

    @Test
    fun `test check holiday return true with state any`() = runTest {
        coEvery { repository.checkHoliday(any(),any(),any(),any()) } returns flow {
            emit(ResultCombine.Success(1))
        }
        HolidayHelper.setState(HolidayState.ANY).checkHoliday("",1,1,2025).collect{
            Assert.assertEquals(it.getOrNull(),true)
        }
    }

    @Test
    fun `test check holiday return true with state consensus`() = runTest {
        coEvery { repository.checkHoliday(any(),any(),any(),any()) } returns flow {
            emit(ResultCombine.Success(2))
        }
        HolidayHelper.setState(HolidayState.CONSENSUS).checkHoliday("",1,1,2025).collect{
            Assert.assertEquals(it.getOrNull(),true)
        }
    }

    @Test
    fun `test check holiday return false when 1 api fail with state all`() = runTest {
        coEvery { repository.checkHoliday(any(),any(),any(),any()) } returns flow {
            emit(ResultCombine.Success(1))
        }
        HolidayHelper.setState(HolidayState.ALL).checkHoliday("",1,1,2025).collect{
            Assert.assertEquals(it.getOrNull(),false)
        }
    }

    @Test
    fun `test check holiday return false with state any`() = runTest {
        coEvery { repository.checkHoliday(any(),any(),any(),any()) } returns flow {
            emit(ResultCombine.Success(0))
        }
        HolidayHelper.setState(HolidayState.ANY).checkHoliday("",1,1,2025).collect{
            Assert.assertEquals(it.getOrNull(),false)
        }
    }

    @Test
    fun `test check holiday return false with state consensus`() = runTest {
        coEvery { repository.checkHoliday(any(),any(),any(),any()) } returns flow {
            emit(ResultCombine.Success(1))
        }
        HolidayHelper.setState(HolidayState.CONSENSUS).checkHoliday("",1,1,2025).collect{
            Assert.assertEquals(it.getOrNull(),false)
        }
    }

    @Test
    fun `test check holiday when 3 api error`() = runTest {
        val dummy = "error"
        coEvery { repository.checkHoliday(any(),any(),any(),any()) } returns flow {
            emit(ResultCombine.Error(dummy))
        }
        HolidayHelper.checkHoliday("",1,1,2025).collect{
            Assert.assertEquals(it.exceptionOrNull()?.message,dummy)
        }
    }
}
