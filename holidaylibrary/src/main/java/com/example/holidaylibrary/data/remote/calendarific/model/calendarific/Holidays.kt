package com.example.holidaylibrary.data.remote.calendarific.model.calendarific

import com.google.gson.annotations.SerializedName


data class Holidays (

    @SerializedName("name"          ) var name         : String?           = null,
    @SerializedName("description"   ) var description  : String?           = null,
    @SerializedName("country"       ) var country      : Country?          = Country(),
    @SerializedName("date"          ) var date         : Date?             = Date(),
    @SerializedName("type"          ) var type         : ArrayList<String> = arrayListOf(),
    @SerializedName("primary_type"  ) var primaryType  : String?           = null,
    @SerializedName("canonical_url" ) var canonicalUrl : String?           = null,
    @SerializedName("urlid"         ) var urlid        : String?           = null,
    @SerializedName("locations"     ) var locations    : String?           = null,
    @SerializedName("states"        ) var states       : String?           = null

)