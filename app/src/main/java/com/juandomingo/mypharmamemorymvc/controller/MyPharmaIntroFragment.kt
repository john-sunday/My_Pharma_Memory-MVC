package com.juandomingo.mypharmamemorymvc.controller

import android.os.Bundle
import android.view.ContentInfo
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentMyPharmaIntroBinding
import com.juandomingo.mypharmamemorymvc.model.Context

class MyPharmaIntroFragment : Fragment(R.layout.fragment_my_pharma_intro) {
    private lateinit var binding: FragmentMyPharmaIntroBinding
    private lateinit var fullName: String
    private lateinit var natCode: String
    private lateinit var expiryDate: String
    private lateinit var adminRoute: String
    private lateinit var ailment: String
    private lateinit var ailmentsList: MutableList<String>
    private lateinit var btnPharmaIntro: Button
    /*************/
    private val database = FirebaseFirestore.getInstance()
    private val user = Firebase.auth.currentUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyPharmaIntroBinding.bind(view)

        Toast.makeText(Context.context, user.hashCode().toString(), Toast.LENGTH_LONG).show()

        fullName = binding.etIntroName.toString()
        natCode = binding.etIntroNatCod.toString()
        expiryDate = binding.etIntroExpiryDate.toString()
        adminRoute = binding.etIntroRoute.toString()
        ailment = binding.etIntroAilments.toString()

        //ailmentsList.toMutableList().add(ailment)
        btnPharmaIntro = binding.btnPharmaIntro
        val userStr = user?.email.toString()

        btnPharmaIntro.setOnClickListener {

        }
    }
}

