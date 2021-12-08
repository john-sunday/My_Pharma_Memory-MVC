package com.juandomingo.mypharmamemorymvc.controller

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentMyPharmaHomeBinding


class MyPharmaHomeFragment : Fragment(R.layout.fragment_my_pharma_home) {
    private lateinit var binding: FragmentMyPharmaHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyPharmaHomeBinding.bind(view)

        binding.btnMyhomeIntro.setOnClickListener {
            val fragment = MyPharmaIntroFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.navHostFragment, fragment).commit()
            transaction.addToBackStack(null)
        }
        binding.btnMyhomeList.setOnClickListener {
            val fragment = MyPharmaListInfoFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.navHostFragment, fragment).commit()
            transaction.addToBackStack(null)
        }
    }

}