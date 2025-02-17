package com.example.holidaylibrary.data.remote.holidayapi.model

import com.example.holidaylibrary.data.remote.model.BaseResponse
import com.google.gson.annotations.SerializedName


data class HolidayApiResponse (

  @SerializedName("status"   ) var status   : Int?                = null,
  @SerializedName("warning"  ) var warning  : String?             = null,
  @SerializedName("requests" ) var requests : Requests?           = Requests(),
  @SerializedName("holidays" ) var holidays : ArrayList<Holidays> = arrayListOf()

): BaseResponse()
