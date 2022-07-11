package com.juandomingo.mypharmamemorymvc.controller

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.model.APIService
import com.juandomingo.mypharmamemorymvc.model.Context
import com.juandomingo.mypharmamemorymvc.model.PharmaResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HtmlPharmaDataFragmentB: Fragment() {
    private val requestKey = "REQUEST_KEY"
    private val code = "CODE"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_html_pharma_data, container, false)
        //return super.onCreateView(inflater, container, savedInstanceState)
        val pharmaNatCode: String? = receiveFullPharmaCode()
        searchByNatCode(pharmaNatCode)
        return view
    }

    private fun receiveFullPharmaCode(): String? {
        var pharmaNatCode: String = ""
        /*parentFragmentManager.setFragmentResultListener(
            requestKey,
            this
        ) { requestKey: String, bundle: Bundle ->*/
            //if (requestKey == this.requestKey) {
            //val fullCodeValue: String? = bundle.getString(code)
            //if (fullCodeValue != null) {

            val args = this.arguments
            val fullCodeValue = args?.get(code)

            pharmaNatCode = fullCodeValue?.let { extractPharmaNationalCode(it as String) }.toString()
            val chArr: CharArray? = fullCodeValue?.toString()?.toCharArray()
            val natCode: CharArray? = CharArray(6)
            var n = 0



            for (i in 10..15) {
                chArr?.get(i)?.let {
                    natCode?.set(n, it)
                    n++
                }
            }
            // Test.
            var str = ""
            if (natCode != null) {
                for (i in natCode.indices) {
                    str = str.plus(natCode[i].toString())
                }
                print(str)
            } else {
                Toast.makeText(Context.context, "No se ha podido leer el código", Toast.LENGTH_LONG)
                    .show()
            }
        //}
        //}
        return pharmaNatCode
    }

    private fun extractPharmaNationalCode(fullCodeValue: String): String {
        val chArr: CharArray? = fullCodeValue?.toCharArray()
        val natCode: CharArray? = CharArray(6)
        var n = 0
        for (i in 10..15) {
            chArr?.get(i)?.let {
                natCode?.set(n, it)
                n++
            }
        }
        // Test.
        var str = ""
        if (natCode != null) {
            for (i in natCode.indices) {
                str = str.plus(natCode[i].toString())
            }
            print(str)
        }
        return str
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cima.aemps.es/cima/rest/medicamento/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByNatCode(query: String?) {
        // Coroutine
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getPharmaByNatCode("?cn=$query")
            val pharma: PharmaResponse? = call.body()

            val call2 = getRetrofit().create(APIService::class.java)

            val caller = call2.getData("https://cima.aemps.es/cima/rest/medicamento?cn=$query")

            if(caller.isSuccessful && caller.code() == 200){
                val body = caller.body()
                Toast.makeText(context, "JSON:\n$body",Toast.LENGTH_LONG).show()

            /*    val docs = pharma?.docs ?: emptyList()
                print(pharma?.docs)*/

            } else {
                showError()
            }




     /*       if (call.isSuccessful) {
                // show html document
                val docs = pharma?.docs ?: emptyList()
                print(pharma?.docs)


            } else {
                showError()
            }*/
        }
    }

    private fun showError() {
        var alertDialog = AlertDialog.Builder(Context.context)
        alertDialog.setTitle("Alerta!")
            .setMessage("Error en la petición al servidor")
            .setCancelable(true)
    }
}