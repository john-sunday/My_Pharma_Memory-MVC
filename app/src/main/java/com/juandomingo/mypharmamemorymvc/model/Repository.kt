package com.juandomingo.mypharmamemorymvc.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Repository {
    fun getPharmaData(): LiveData<MutableList<PharmacoModel>> {
        val mutableData = MutableLiveData<MutableList<PharmacoModel>>()
        FirebaseFirestore.getInstance()
            .collection(FirebaseAuth.getInstance().currentUser?.email.toString())
            .get()
            .addOnSuccessListener {
                val userPharmaList = mutableListOf<PharmacoModel>()
                for (document in it) {
                    val docList = it.documents
                    /*val pharmaData: Map<String, Any> =  document.data
                    val fullName: String = pharmaData["Nombre completo"] as String
                    val natCode = pharmaData["Código nacional"]
                    val expiryDate: String = pharmaData["Fecha de caducidad"] as String
                    val adminRoute = pharmaData["Vía de administración"] as String
                    val ailmentsList = pharmaData["Dolencias que trata"] as MutableList<*>*/
                    // Android Society way.
                    val fullName: String? = document.getString("Nombre completo")
                    val natCode: Number = document.get("Código nacional") as Number
                    val expiryDate: String? = document.getString("Fecha de caducidad")
                    val adminRoute: String? = document.getString("Vía de administración")
                    val ailmentsList: MutableList<*>? = document.get("Dolencias que trata") as MutableList<*>?

                    val pharma = PharmacoModel(fullName!!, natCode as Long, expiryDate!!, adminRoute!!, ailmentsList!!)
                    userPharmaList.add(pharma)
                }
                mutableData.value = userPharmaList
            }
        return mutableData
    }
}