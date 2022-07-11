package com.juandomingo.mypharmamemorymvc.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class PharmaResponse(
    @SerializedName("nregistro") var nregistro: String,
    @SerializedName("docs") var docs: List<String>,
    @SerializedName("presentaciones")var presentaciones: List<String>
    )
