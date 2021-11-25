package com.juandomingo.mypharmamemory.data.network

import com.juandomingo.mypharmamemory.data.model.PharmacoModel
import retrofit2.Response
import retrofit2.http.GET

/*  Esta Interface sirve para terminar de configurar el proceso
*   de llamada a Firebase, es decir, hacer esa llamada REST.    */
interface PharmaApiClient {
    @GET("/.json")
    // Función SUSPENDIDA por el uso de CORRUTINAS.
    suspend fun getAllPharma(): Response<List<PharmacoModel>>
}