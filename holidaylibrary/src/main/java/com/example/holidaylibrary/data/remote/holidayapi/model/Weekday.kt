package com.example.holidaylibrary.data.remote.holidayapi.model

import com.google.gson.annotations.SerializedName


data class Weekday (

    @SerializedName("date"     ) var date     : Date?     = Date(),
    @SerializedName("observed" ) var observed : Observed? = Observed()

)