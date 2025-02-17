package com.example.holidaylibrary.data.remote.calendarific.model.calendarific

import com.google.gson.annotations.SerializedName


data class Date (

  @SerializedName("iso"      ) var iso      : String?   = null,
  @SerializedName("datetime" ) var datetime : Datetime? = Datetime()

)