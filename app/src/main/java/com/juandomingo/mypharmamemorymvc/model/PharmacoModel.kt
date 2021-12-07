package com.juandomingo.mypharmamemorymvc.model

data class PharmacoModel(
    val fullName: String,
    val natCode: String,
    val expiryDate: String,
    val routeAdmin: String,
    val ailmentsList: MutableList<String>)