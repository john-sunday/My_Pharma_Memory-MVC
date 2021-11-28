package com.juandomingo.mypharmamemorymvc.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentLectorPharmaHtmlBinding


class LectorPharmaHtmlFragment : Fragment(R.layout.fragment_lector_pharma_html) {
    private lateinit var binding: FragmentLectorPharmaHtmlBinding
    private lateinit var readCodeTv: TextView
    private val requestKey = "REQUEST_KEY"
    private val code = "CODE"
    companion object {
        fun newInstance() = LectorPharmaHtmlFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLectorPharmaHtmlBinding.bind(view)
        readCodeTv = binding.readedCodeTv

        parentFragmentManager.setFragmentResultListener(requestKey, this) { requestKey: String, bundle: Bundle ->
            val codeValue = bundle.getString(code)
            readCodeTv.setText(codeValue).toString()
        }
    }

}