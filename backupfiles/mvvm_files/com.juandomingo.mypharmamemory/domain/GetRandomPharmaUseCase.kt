package com.juandomingo.mypharmamemorymvc.domain

import com.juandomingo.mypharmamemorymvc.data.model.PharmaProvider
import com.juandomingo.mypharmamemorymvc.model.PharmacoModel
import javax.inject.Inject

// 4º paso Dagger Hilt -> preparamos las clases de casos de uso
class GetRandomPharmaUseCase @Inject constructor(
    private val pharmaProvider: PharmaProvider
) {
    //private val repository = PharmaRepository()
    operator fun invoke(): PharmacoModel? {
        val pharmas = pharmaProvider.pharmas
        /*  TODO hacer la val randomNumber global, para tener que
        *   escribir un solo return.    */
        if (!pharmas.isNullOrEmpty()) {
            // .... = (0..pharmas.size - 1).random()
            val randomNumber = (pharmas.indices).random()
            return pharmas[randomNumber]
        }
        return null
    }
}