package com.juandomingo.mypharmamemorymvc.controller

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentAppHomeBinding


class AppHomeFragment : Fragment(R.layout.fragment_app_home) {
    private lateinit var binding: FragmentAppHomeBinding
    companion object {
        fun newInstance() = AppHomeFragment()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAppHomeBinding.bind(view)

    }
}