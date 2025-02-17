package com.example.holidaylibrary.data.remote.holidayapi.model

import com.google.gson.annotations.SerializedName


data class Holidays (

  @SerializedName("name"     ) var name     : String?  = null,
  @SerializedName("date"     ) var date     : String?  = null,
  @SerializedName("observed" ) var observed : String?  = null,
  @SerializedName("public"   ) var public   : Boolean? = null,
  @SerializedName("country"  ) var country  : String?  = null,
  @SerializedName("uuid"     ) var uuid     : String?  = null,
  @SerializedName("weekday"  ) var weekday  : Weekday? = Weekday()

)