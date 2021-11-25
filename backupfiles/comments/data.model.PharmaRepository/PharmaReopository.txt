package com.juandomingo.mypharmamemory.data

import com.juandomingo.mypharmamemory.data.model.PharmaProvider
import com.juandomingo.mypharmamemory.data.model.Pharmaco
import com.juandomingo.mypharmamemory.data.model.PharmacoModel
import com.juandomingo.mypharmamemory.data.network.PharmaService
import javax.inject.Inject

// 5º paso Dagger Hilt -> preparamos this.class
class PharmaRepository @Inject constructor(
    private val api: PharmaService,
    private val pharmaProvider: PharmaProvider
) {
    /*  La primera vez que llamemos al repositorio -> this.class, desde aquí, llamaremos
    *   a la clase 'data/network/PharmaService.kt', que llamará al
    *   'data/network/PharmaApiClient.kt.getAllPharma()', y la lista de fármacos serán
    *   enviados de vuelta al Service, y a su vez, devueltos al Repository, y esta clase
    *   PharmaRepository.kt le dirá a la variable pharma del Provider, que guarde
    *   la response, que contiene la lista de todos los fármacos. Finálmente el método
    *   'this.class.getAllPharma()' retornará 'List<Pharmaco>'.
    *   */
    //private val api = PharmaService()
    suspend fun getAllPharma(): List<PharmacoModel> {
        val response: List<PharmacoModel> = api.getPharma()
        /*  Guardaremos, DE MOMENTO, la respuesta en PharmaProvider, simulando a
            la clase Provider como una db.    */
        pharmaProvider.pharmas = response
        return response
    }

    fun addNewMyPharma(pharmaco: Pharmaco) {

    }
}