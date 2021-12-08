package com.juandomingo.mypharmamemorymvc.controller

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentMyPharmaIntroBinding
import com.juandomingo.mypharmamemorymvc.model.Context
import com.juandomingo.mypharmamemorymvc.model.PharmacoModel

class MyPharmaIntroFragment : Fragment(R.layout.fragment_my_pharma_intro) {
    private lateinit var binding: FragmentMyPharmaIntroBinding
    private lateinit var fullName: String
    private lateinit var natCode: String
    private lateinit var expiryDateStr: String
    private lateinit var adminRoute: String
    private lateinit var ailment: String
    private lateinit var ailmentsList: MutableList<String>
    //private lateinit var lvAilments: ListView
    //private lateinit var arrayAdapter: ArrayAdapter<*>
    private lateinit var btnPharmaIntro: Button
    private val database = FirebaseFirestore.getInstance()
    private val user = Firebase.auth.currentUser


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyPharmaIntroBinding.bind(view)
        //Toast.makeText(Context.context, user?.email, Toast.LENGTH_LONG).show()
        //lvAilments = binding.lvAilments
        //ailmentsList.add(ailment)
        //ailmentsList.toMutableList().add(ailment)
        btnPharmaIntro = binding.btnPharmaIntro
        val userEmail = user?.email.toString()

        btnPharmaIntro.setOnClickListener {
            fullName = binding.etIntroName.text.toString()
            natCode = binding.etIntroNatCod.text.toString()
            expiryDateStr = binding.etIntroExpiryDate.text.toString()
            adminRoute = binding.etIntroRoute.text.toString()
            ailment = binding.etIntroAilments.text.toString()
            if (checkFields()){
                ailmentsList = mutableListOf(ailment)
                //arrayAdapter = ArrayAdapter(Context.context, android.R.layout.simple_list_item_1, ailmentsList)
                //lvAilments.adapter = arrayAdapter
                //val pharma = PharmacoModel(fullName, natCode.toInt(), expiryDateStr, adminRoute, ailmentsList)

                val pharmaHash = hashMapOf(
                    "Nombre completo" to fullName,
                    "Código nacional" to natCode.toInt(),
                    "Fecha de caducidad" to expiryDateStr,
                    "Vía de administración" to adminRoute,
                    "Dolencias que trata" to ailmentsList
                )
                database.collection(userEmail).document(fullName).set(pharmaHash).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(Context.context, "Fármaco guardado exitosamente", Toast.LENGTH_LONG).show()
                        setAllFieldsVoid()
                    } else {
                        Toast.makeText(Context.context, "Error al guardar el fármaco", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                //Toast.makeText(Context.context, "Falta algún campo por rellenar", Toast.LENGTH_LONG).show()
                showError("Falta algún campo por rellenar")
            }
        }
    }

    private fun checkFields(): Boolean {
        var allFilled = false
        if (!isFieldEmpty(fullName)
            && !isFieldEmpty(natCode)
            && !isFieldEmpty(expiryDateStr)
            && !isFieldEmpty(adminRoute)
            && !isFieldEmpty(ailment)) {
            allFilled = true
        }
        return allFilled
    }
    private fun showError(message: String) {
        val alertDialog = AlertDialog.Builder(activity!!)
        alertDialog
            .setTitle("Alerta!")
            .setMessage(message)
            .setCancelable(true)
            .setPositiveButton(android.R.string.ok){
                    _, _ ->
                //Toast.makeText(Context.context, "Téngalo en cuenta", Toast.LENGTH_LONG).show()
            }
            .show()
    }
    private fun isFieldEmpty(string: String): Boolean {
        var isEmpty = true
        if (!(TextUtils.isEmpty(string))) {
            isEmpty = false
        }
        return isEmpty
    }
    private fun setAllFieldsVoid() {
        val voidStr = ""
        binding.etIntroName.setText(voidStr)
        binding.etIntroNatCod.setText(voidStr)
        binding.etIntroExpiryDate.setText(voidStr)
        binding.etIntroRoute.setText(voidStr)
        binding.etIntroAilments.setText(voidStr)
    }
}

