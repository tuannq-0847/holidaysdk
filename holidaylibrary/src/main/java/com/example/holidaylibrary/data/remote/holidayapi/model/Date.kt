package com.example.holidaylibrary.data.remote.holidayapi.model

import com.google.gson.annotations.SerializedName


data class Date (

  @SerializedName("name"    ) var name    : String? = null,
  @SerializedName("numeric" ) var numeric : String? = null

)