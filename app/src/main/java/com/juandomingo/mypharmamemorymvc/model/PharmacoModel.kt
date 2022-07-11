package com.juandomingo.mypharmamemorymvc.model

data class PharmacoModel(
    // Inicializamos los parámetros para poder construir constructor vacío.
    val fullName: String = "DEFAULT_NAME",
    val natCode: Number = 0,
    val expiryDate: String = "DEFAULT_EXPIRY_DATE",
    val routeAdmin: String = "DEFAULT_ROUTE_ADMIN",
    val ailmentsList: MutableList<*> = mutableListOf("DEFAULT_FIRST_VALUE"))