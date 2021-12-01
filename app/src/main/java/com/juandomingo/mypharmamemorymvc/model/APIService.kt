package com.juandomingo.mypharmamemorymvc.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPharmaByNatCode(@Url url: String): Response<PharmaResponse>
}