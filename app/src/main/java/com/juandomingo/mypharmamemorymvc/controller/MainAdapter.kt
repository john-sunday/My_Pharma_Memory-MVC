package com.juandomingo.mypharmamemorymvc.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juandomingo.mypharmamemorymvc.R
import com.juandomingo.mypharmamemorymvc.model.PharmacoModel

/** Esta clase es el corazón del RecyclerView.
 *  Recibe un array con los items, y se los envía al RecyclerView, para
 *  que éste los muestre.   **/
class MainAdapter(private val context: Context):  RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private var dataList = mutableListOf<PharmacoModel>()
    fun setListData(data: MutableList<PharmacoModel>){
        dataList = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        // La 'view' es la 'inner class MainViewHolder'.
        val view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val pharma = dataList[position]
        holder.bindView(pharma)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0) {
            dataList.size
        } else {
            0
        }
    }

    /*  Ventaja: clase fija de la clase MainAdapter.
    *   Cuando finalice la instancia de MainAdapter, finaliza también la inner class.
    *   Puede obtener parámetros privados de la clase padre, como 'context'.    */
    inner class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(pharma: PharmacoModel) {
            Glide.with(context).load(R.drawable.pills2).into(itemView.findViewById(R.id.circle_imageview))
            itemView.findViewById<TextView>(R.id.tv_item_fullname).text = pharma.fullName
            itemView.findViewById<TextView>(R.id.tv_item_expirydate).text = pharma.expiryDate
        }
    }
}