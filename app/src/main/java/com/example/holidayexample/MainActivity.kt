package com.example.holidayexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.lifecycleScope
import com.example.holidaylibrary.helper.HolidayHelper
import com.example.holidaylibrary.helper.HolidayState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.TimeZone

class MainActivity : AppCompatActivity() {

    private var holidayState = HolidayState.ANY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.setFragmentResultListener(
            "datepicker", this
        ) { requestKey, result ->
            val year = result.getInt("year")
            val month = result.getInt("month")
            val day = result.getInt("day")
            val text = "day $day, month: $month, year: $year"
            findViewById<TextView>(R.id.text_tit).text = "Result: $text"
            lifecycleScope.launch {
                HolidayHelper.setState(holidayState).checkHoliday(
                    countryCode = "VN",
                    day = day,
                    month = month,
                    year = year
                )
                    .onStart { findViewById<TextView>(R.id.text_content).text = "Loading..." }
                    .collect { showMessage(it) }
            }
        }
        val state1 = findViewById<CheckBox>(R.id.state1)
        state1.isClickable = false
        val state2 = findViewById<CheckBox>(R.id.state2)
        val state3 = findViewById<CheckBox>(R.id.state3)
        var lastChecked: CheckBox? = null
        fun onCheckboxClicked(checkedBox: CheckBox) {
            if (lastChecked == checkedBox) {
                checkedBox.isChecked = true
                return
            }

            state1.isChecked = false
            state2.isChecked = false
            state3.isChecked = false
            holidayState = when (checkedBox) {
                state3 -> {
                    HolidayState.CONSENSUS
                }

                state2 -> {
                    HolidayState.ALL
                }

                else -> {
                    HolidayState.ANY
                }
            }
            checkedBox.isChecked = true
            lastChecked = checkedBox
        }

        // Set onClickListeners for each checkbox
        state1.setOnClickListener { onCheckboxClicked(state1) }
        state2.setOnClickListener { onCheckboxClicked(state2) }
        state3.setOnClickListener { onCheckboxClicked(state3) }
        findViewById<Button>(R.id.button).setOnClickListener {
            supportFragmentManager.beginTransaction().add(DatePickerFragment(), null).commit()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showMessage(state: Result<Boolean>) {
        if (state.isSuccess) {
            findViewById<TextView>(R.id.text_content).text = "${state.getOrNull()}\n"
        }
        if (state.isFailure) {
            findViewById<TextView>(R.id.text_content).text = "${state.exceptionOrNull()?.message}\n"
        }
    }
}