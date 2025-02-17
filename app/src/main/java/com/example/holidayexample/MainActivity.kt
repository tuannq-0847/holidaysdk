package com.example.holidayexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.holidaylibrary.helper.HolidayHelper
import com.example.holidaylibrary.helper.HolidayState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.TimeZone

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.enter_button1).setOnClickListener {
            lifecycleScope.launch {
                HolidayHelper.checkHoliday("VN", 1, 1, 2025)
                    .onStart {
                        findViewById<TextView>(R.id.result).text = "Loading...."
                    }
                    .collectLatest {
                    showMessage(it)
                }
            }
        }
        findViewById<Button>(R.id.enter_button2).setOnClickListener {
            lifecycleScope.launch {
                HolidayHelper.setState(HolidayState.ALL).checkHoliday("VN", 1, 1, 2025)
                    .onStart {
                        findViewById<TextView>(R.id.result).text = "Loading...."
                    }
                    .collectLatest {
                    showMessage(it)
                }
            }
        }
        findViewById<Button>(R.id.enter_button3).setOnClickListener {
            lifecycleScope.launch {
                HolidayHelper.setState(HolidayState.ALL).checkHoliday("VN", 10, 1, 2025)
                    .onStart {
                        findViewById<TextView>(R.id.result).text = "Loading...."
                    }
                    .collectLatest {
                        showMessage(it)
                    }
            }
        }
        findViewById<Button>(R.id.enter_button4).setOnClickListener {
            lifecycleScope.launch {
                HolidayHelper.setState(HolidayState.CONSENSUS).checkHoliday("VN", 1, 1, 2025)
                    .onStart {
                        findViewById<TextView>(R.id.result).text = "Loading...."
                    }
                    .collectLatest {
                        showMessage(it)
                    }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showMessage(state: Result<Boolean>){
        if (state.isSuccess) {
            findViewById<TextView>(R.id.result).text = "Result:\n${state.getOrNull()}"
        }
        if (state.isFailure) {
            findViewById<TextView>(R.id.result).text = "Result:\n${state.exceptionOrNull()?.message}"
        }
    }
}