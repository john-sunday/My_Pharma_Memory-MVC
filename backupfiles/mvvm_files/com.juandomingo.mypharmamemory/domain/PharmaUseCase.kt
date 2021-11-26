package com.juandomingo.mypharmamemorymvc.domain

import javax.inject.Inject

class PharmaUseCase @Inject constructor() {
    /*  Los datos del repo externo se acabarán inyectando. De momento, guardamos
    *   en Provider los datos.  */
    //private val repository = PharmaRepository()
    /*  La palabra reservada 'invoke', sirve para que al llamar
    *   a this.class, automáticamente se ejecute esta función.  */
    //suspend operator fun invoke(): List<Pharmaco>? = repository.getAllPharma()
}