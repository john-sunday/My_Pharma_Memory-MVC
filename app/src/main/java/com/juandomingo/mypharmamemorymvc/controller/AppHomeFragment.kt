package com.juandomingo.mypharmamemorymvc.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.juandomingo.mypharmamemorymvc.databinding.FragmentAppHomeBinding
import com.juandomingo.mypharmamemorymvc.model.AppHomeViewModel


class AppHomeFragment : Fragment() {
    private var _binding: FragmentAppHomeBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = AppHomeFragment()
    }
    private lateinit var viewModel: AppHomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAppHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

}