package com.juandomingo.mypharmamemory.data.model

import com.google.gson.annotations.SerializedName

/*  Usaremos este modelo como respuesta de Retrofit. es decir,
*   la información que recuperemos de Retrofit, la convertiremos
*   en este modelo de datos. */
data class PharmacoModel(
    // Por si los parámetros no se llaman igual, serializamos éstos:
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String)