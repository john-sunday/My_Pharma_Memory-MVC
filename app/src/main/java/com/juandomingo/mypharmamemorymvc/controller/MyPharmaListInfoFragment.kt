package com.juandomingo.mypharmamemorymvc.controller

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.databinding.FragmentMyPharmaListInfoBinding
import com.juandomingo.mypharmamemorymvc.model.Context
import com.juandomingo.mypharmamemorymvc.model.MainViewModel


class MyPharmaListInfoFragment : Fragment(R.layout.fragment_my_pharma_list_info) {
    private lateinit var binding: FragmentMyPharmaListInfoBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    /*  Con 'by lazy' inicializamos la variable, unicamente cuando es estrictamente necesario
    *   y no así no tener 500 variables inicializadas que no necesitamos, ocupan memoria,
    *   y afectan al rendimiento de la aplicación.  */
    private lateinit var mainViewModel: MainViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyPharmaListInfoBinding.bind(view)

        adapter = MainAdapter(Context.context)

        recyclerView = binding.rvMylistPharmalist
        recyclerView.layoutManager = LinearLayoutManager(Context.context)
        // Podremos scrollear el RecyclerView tanto horizontal como verticalmente.
        recyclerView.adapter = adapter

        observeData()

        /*  Lista de fármacos introducidas a capón.
        * val userPharmaList = mutableListOf<PharmacoModel>()
          userPharmaList.add(PharmacoModel("Condrosan 400mg", 815, "2025/06/29", "Oral", mutableListOf("Articulaciones")))
          adapter.setListData(userPharmaList)
          adapter.notifyDataSetChanged()
        * */
    }

    fun observeData() {
        mainViewModel = MainViewModel()
        mainViewModel.fetchPharmaData().observe(this, Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        })
    }
}