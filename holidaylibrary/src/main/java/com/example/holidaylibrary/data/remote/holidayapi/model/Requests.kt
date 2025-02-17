package com.example.holidaylibrary.data.remote.holidayapi.model

import com.google.gson.annotations.SerializedName


data class Requests (

  @SerializedName("used"      ) var used      : Int?    = null,
  @SerializedName("available" ) var available : Int?    = null,
  @SerializedName("resets"    ) var resets    : String? = null

)