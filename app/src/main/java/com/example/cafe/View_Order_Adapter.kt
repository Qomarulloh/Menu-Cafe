package com.example.cafe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_view_order.view.*

class View_Order_Adapter(mCtx: Context, val ViewOrder: ArrayList<Order_Menu_Model>): RecyclerView.Adapter<View_Order_Adapter.ViewHolder>() {

    val mCtx = mCtx
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val Nmenu = itemView.text_namaV
        val Harga = itemView.text_hargaV
        val jmlOrder = itemView.text_jmlOrderV
        val total = itemView.text_totalHargaV
        val hapus = itemView.btn_delete

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.list_item_view_order, p0, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val Orderan = ViewOrder[p1]
        p0.Nmenu.text = Orderan.str_nmMenu
        p0.Harga.text = Orderan.int_hrgMenu.toString()
        p0.jmlOrder.text = Orderan.int_jmlOrder.toString()
        p0.total.text = Orderan.int_totalHarga.toString()
    }

    override fun getItemCount(): Int {
        return ViewOrder.size
    }
}