package com.example.holidaylibrary.data.remote.calendarific.model.calendarific

import com.google.gson.annotations.SerializedName


data class Response (

  @SerializedName("holidays" ) var holidays : ArrayList<Holidays> = arrayListOf()

)