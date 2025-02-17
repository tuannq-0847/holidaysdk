package com.example.holidaylibrary.data.remote.calendarific.model.calendarific

import com.google.gson.annotations.SerializedName


data class Datetime (

  @SerializedName("year"  ) var year  : Int? = null,
  @SerializedName("month" ) var month : Int? = null,
  @SerializedName("day"   ) var day   : Int? = null

)