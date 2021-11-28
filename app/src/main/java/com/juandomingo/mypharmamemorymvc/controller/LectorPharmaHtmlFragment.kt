package com.juandomingo.mypharmamemorymvc.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentLectorPharmaHtmlBinding
import com.juandomingo.mypharmamemorymvc.model.Context
import java.util.stream.IntStream.range


class LectorPharmaHtmlFragment : Fragment(R.layout.fragment_lector_pharma_html) {
    private lateinit var binding: FragmentLectorPharmaHtmlBinding
    private lateinit var readCodeTv: TextView
    private val requestKey = "REQUEST_KEY"
    private val code = "CODE"
    private lateinit var pharmaNatCode: String
    companion object {
        fun newInstance() = LectorPharmaHtmlFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLectorPharmaHtmlBinding.bind(view)
        readCodeTv = binding.readedCodeTv
        receiveFullPharmaCode()
        // https://cima.aemps.es/cima/rest/medicamento?cn=767491 ejemplo petición para 'Omeprazol STADA cn=767491'
    }

    private fun receiveFullPharmaCode() {
        parentFragmentManager.setFragmentResultListener(
            requestKey,
            this
        ) { requestKey: String, bundle: Bundle ->
            val fullCodeValue: String? = bundle.getString(code)
            readCodeTv.setText(fullCodeValue).toString()
            if (fullCodeValue != null) {
                pharmaNatCode = extractPharmaNationalCode(fullCodeValue)
            } else {
                Toast.makeText(Context.context, "No se ha podido leer el código", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
    private fun extractPharmaNationalCode(fullCodeValue: String): String {
        val chArr: CharArray? = fullCodeValue?.toCharArray()
        val natCode: CharArray? = CharArray(6)
        var n: Int = 0
        for (i in 10..15) {
            chArr?.get(i)?.let {
                natCode?.set(n, it)
                n++
            }
        }
        // Test.

        var str: String = ""
        if (natCode != null) {
            for (i in natCode.indices) {
                print("*********   ${natCode[i]}")
                str = str.plus(natCode[i].toString())
            }
            print(str)
        }
        return str
    }
}
