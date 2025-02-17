package com.example.holidaylibrary.data.remote.abstractapi.model

import com.example.holidaylibrary.data.remote.model.BaseResponse
import com.google.gson.annotations.SerializedName


data class ResponseAbstractApi (

  @SerializedName("name"        ) var name        : String? = null,
  @SerializedName("name_local"  ) var nameLocal   : String? = null,
  @SerializedName("language"    ) var language    : String? = null,
  @SerializedName("description" ) var description : String? = null,
  @SerializedName("country"     ) var country     : String? = null,
  @SerializedName("location"    ) var location    : String? = null,
  @SerializedName("type"        ) var type        : String? = null,
  @SerializedName("date"        ) var date        : String? = null,
  @SerializedName("date_year"   ) var dateYear    : String? = null,
  @SerializedName("date_month"  ) var dateMonth   : String? = null,
  @SerializedName("date_day"    ) var dateDay     : String? = null,
  @SerializedName("week_day"    ) var weekDay     : String? = null

): BaseResponse()