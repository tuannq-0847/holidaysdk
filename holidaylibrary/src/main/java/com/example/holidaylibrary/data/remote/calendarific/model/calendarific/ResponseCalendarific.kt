package com.example.holidaylibrary.data.remote.calendarific.model.calendarific

import com.example.holidaylibrary.data.remote.model.BaseResponse
import com.google.gson.annotations.SerializedName


data class ResponseCalendarific (

  @SerializedName("meta"     ) var meta     : Meta?     = Meta(),
  @SerializedName("response" ) var response : Response? = Response()

): BaseResponse()