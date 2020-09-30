package com.example.cafe

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        p0.hapus.setImageResource(R.drawable.ic_baseline_delete_24)

        p0.hapus.setOnClickListener{
            val menuName = Orderan.str_nmMenu
            var alertDialog = AlertDialog.Builder(mCtx).setTitle("Delete")
                .setMessage("Yakin ingin hapus data : $menuName ?")
                .setPositiveButton("Yes", DialogInterface.OnClickListener{
                dialog, which -> if (MainActivity.dbHandler.deleteOrder(Orderan.str_id)){
                ViewOrder.removeAt(p1)
                notifyItemRemoved(p1)
                notifyItemRangeChanged(p1, ViewOrder.size)
                Toast.makeText(mCtx, "Barang $menuName dihapus", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(mCtx, "Terjadi kesalahan penghapusan", Toast.LENGTH_SHORT).show()
            }
            })
                .setNegativeButton("No", DialogInterface.OnClickListener{
                    dialog, which ->
                }).setIcon(R.drawable.ic_baseline_warning_24).show()
        }
    }

    override fun getItemCount(): Int {
        return ViewOrder.size
    }
}