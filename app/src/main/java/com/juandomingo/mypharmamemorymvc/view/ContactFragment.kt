package com.juandomingo.mypharmamemorymvc.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.juandomingo.mypharmamemorymvc.R

class ContactFragment : Fragment() {

    companion object {
        fun newInstance() = ContactFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }



}