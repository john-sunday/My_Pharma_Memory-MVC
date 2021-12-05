package com.juandomingo.mypharmamemorymvc.controller

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.model.Context

class CodeLectorFragmentB: Fragment() {
    private val requestKey = "REQUEST_KEY"
    private val code = "CODE"



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_code_lector, container, false)
        //return super.onCreateView(inflater, container, savedInstanceState)
        initScanner()

        return view
    }

    private fun initScanner() {
        val intent: IntentIntegrator = IntentIntegrator.forSupportFragment(this)
        intent.setDesiredBarcodeFormats(IntentIntegrator.DATA_MATRIX)
        intent.setPrompt("CODE SCAN")
        //intent.setCameraId(0)
        intent.setBeepEnabled(true)
        //intent.setBarcodeImageEnabled(false)
        intent.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents != null){
           // Toast.makeText(Context.context, "Escaneo cancelado", Toast.LENGTH_LONG).show()

            Toast.makeText(Context.context, "Código: ${result.contents}", Toast.LENGTH_LONG)
                .show()
            val bundle = Bundle()

            bundle.putString(code,result.contents.toString())
            val lectorPharmaHtmlFragment = LectorPharmaHtmlFragmentA()
            lectorPharmaHtmlFragment.arguments = bundle

            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.navHostFragment, lectorPharmaHtmlFragment).commit()
            //setFragmentResult(requestKey, bundleOf(code to result)) PROBAR SI NO FUNCIONA LA INSTRUCCIÓN DE ABAJO !!!!
            //parentFragmentManager.setFragmentResult(requestKey, bundle)
            //navigateFromCodeLectorToPharmaHtml()
            //NavHostFragment.findNavController(this).navigate(R.id.action_codeLectorFragment_to_pharmaLectorHtmlFragment)
        }
    }

    private fun navigateFromCodeLectorToPharmaHtml() {
        val fragment = LectorPharmaHtmlFragmentB()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.navHostFragment, fragment).commit()
        //transaction.addToBackStack(null)
    }

}