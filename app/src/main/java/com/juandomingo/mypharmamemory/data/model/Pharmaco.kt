package com.juandomingo.mypharmamemory.data.model

import com.google.gson.annotations.SerializedName

/*  Usaremos este modelo como respuesta de Retrofit. es decir,
*   la información que recuperemos de Retrofit, la convertiremos
*   en este modelo de datos. */
data class Pharmaco(
    // Por si los parámetros no se llaman igual, serializamos éstos:
    @SerializedName("full_name") val fullName: String,
    @SerializedName("nac_code") val nacCode: Int,
    @SerializedName("expiry_date") val expiryDate: String,
    @SerializedName("admin_route") val adminRoute: String,
    @SerializedName("ailments") val ailments: List<String>) {

    constructor() : this("", 0, "", "", listOf<String>())
}