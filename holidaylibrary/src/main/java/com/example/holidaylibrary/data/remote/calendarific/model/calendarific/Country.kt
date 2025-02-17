package com.example.holidaylibrary.data.remote.calendarific.model.calendarific

import com.google.gson.annotations.SerializedName


data class Country (

  @SerializedName("id"   ) var id   : String? = null,
  @SerializedName("name" ) var name : String? = null

)