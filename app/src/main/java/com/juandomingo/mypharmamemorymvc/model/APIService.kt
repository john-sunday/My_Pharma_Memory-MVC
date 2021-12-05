package com.juandomingo.mypharmamemorymvc.model

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPharmaByNatCode(@Url url: String): Response<PharmaResponse>

    @GET
    suspend fun getData(@Url url: String): Response<JsonObject>
}