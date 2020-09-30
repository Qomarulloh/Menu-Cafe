package com.example.cafe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Menu_Cafe_Adapter(context: Context, DaftarMenu: ArrayList<Menu_Cafe_Model>) :BaseAdapter() {
    private val DaftarMenu: ArrayList<Menu_Cafe_Model>
    private val mInflater: LayoutInflater

    init {
        this.mInflater = LayoutInflater.from(context)
        this.DaftarMenu = DaftarMenu
    }

    override fun getCount(): Int {
        return DaftarMenu.size
    }

    override fun getItem(position: Int): Any {
        return DaftarMenu.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListDaftarMenu
        if (convertView == null){
            view = this.mInflater.inflate(R.layout.list_item_menu, parent, false)
            vh = ListDaftarMenu(view)
            view.tag = vh
        }
        else
        {
            view = convertView
            vh = view.tag as ListDaftarMenu
        }
        vh.mnNama.text = DaftarMenu.get(position).str_nama
        vh.mnDesk.text = DaftarMenu.get(position).str_des
        vh.mnHarga.text = DaftarMenu.get(position).int_harga.toString()
        vh.mnImage.setImageResource(DaftarMenu.get(position).int_image)
        return view
    }
}

private class ListDaftarMenu(row: View?) {
    public val mnNama: TextView
    public val mnDesk: TextView
    public val mnHarga: TextView
    public val mnImage: ImageView

    init {
        this.mnNama = row?.findViewById(R.id.textNamaMenu) as TextView
        this.mnDesk = row?.findViewById(R.id.textDeskripsi) as TextView
        this.mnHarga = row?.findViewById(R.id.textHarga) as TextView
        this.mnImage = row?.findViewById(R.id.imgMenu) as ImageView
    }
}