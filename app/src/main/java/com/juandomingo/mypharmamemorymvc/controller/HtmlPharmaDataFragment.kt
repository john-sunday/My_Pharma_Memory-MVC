package com.juandomingo.mypharmamemorymvc.controller

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentHtmlPharmaDataBinding
import com.juandomingo.mypharmamemorymvc.model.APIService
import com.juandomingo.mypharmamemorymvc.model.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HtmlPharmaDataFragment : Fragment(R.layout.fragment_html_pharma_data) {
    private lateinit var binding: FragmentHtmlPharmaDataBinding
    private val requestKey = "REQUEST_KEY"
    private val code = "CODE"
    private lateinit var webviewLeaflet: WebView
    private val url: String = "https://cima.aemps.es/cima/rest/medicamento/"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webviewLeaflet = view.findViewById(R.id.webview_leaflet)
        binding = FragmentHtmlPharmaDataBinding.bind(view)
        val bundle = arguments
        val pharmaFullCode: String? = bundle?.getString(code)
        val natCode = pharmaFullCode?.let { extractPharmaNationalCode(it) }
        //Toast.makeText(context, "COD NAT: $natCode", Toast.LENGTH_SHORT).show()
        searchByNatCode(natCode)
        // Ejemplo petición para 'Omeprazol STADA cn=767491' (funciona con '/?')
        // https://cima.aemps.es/cima/rest/medicamento/?cn=767491
    }

    private fun extractPharmaNationalCode(fullCodeValue: String): String {
        val chArr: CharArray? = fullCodeValue.toCharArray()
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
            val call = getRetrofit().create(APIService::class.java)
            val caller = call.getData("$url?cn=$query")
            if (caller.isSuccessful && caller.code() == 200) {
                val body = caller.body()
                if (body != null) {
                    val obj: JsonObject = body.asJsonObject
                    val jsonArray: JsonArray = obj.get("docs").asJsonArray
                    val urlHtml = jsonArray[1].asJsonObject.get("urlHtml").asString
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(urlHtml)
                    startActivity(intent)
                }

            } else {
                showError()
            }
        }
    }

    private fun showError() {
        var alertDialog = AlertDialog.Builder(Context.context)
        alertDialog.setTitle("Alerta!")
            .setMessage("Error en la petición al servidor")
            .setCancelable(true)
    }
}
