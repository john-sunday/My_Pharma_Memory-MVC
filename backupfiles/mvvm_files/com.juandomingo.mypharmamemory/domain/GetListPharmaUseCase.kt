package com.juandomingo.mypharmamemorymvc.domain

import com.juandomingo.mypharmamemorymvc.data.PharmaRepository
import com.juandomingo.mypharmamemorymvc.model.PharmacoModel
import javax.inject.Inject

// 4º paso Dagger Hilt -> preparamos las clases de casos de uso
/*  En vez de crear en esta clase una instancia de PharmaRepository(),
*   la inyectamos:  */
class GetListPharmaUseCase @Inject constructor(
    private val repository: PharmaRepository
) {
    /*  Los datos del repo externo se acabarán inyectando. De momento, guardamos
    *   en Provider los datos.  */
    //private val repository = PharmaRepository()
    /*  La palabra reservada 'invoke', sirve para que al llamar
    *   a this.class, automáticamente se ejecute esta función.  */
    suspend operator fun invoke(): List<PharmacoModel>? = repository.getAllPharma()
}