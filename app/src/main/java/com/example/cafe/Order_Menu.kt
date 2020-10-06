package com.example.cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order__menu.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Order_Menu : AppCompatActivity() {

    private lateinit var nm_menu: TextView
    private lateinit var hg_manu: TextView
    private lateinit var img_menu: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order__menu)

        val tglSystem = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        text_tanggal.setText(tglSystem)

        val jamSystem = LocalDateTime.now()
        val formated = jamSystem.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        text_jam.setText(formated)

        val intent = intent
        val receivedName = intent.getStringExtra("nama_menu")
        val receivedHarga = intent.getStringExtra("harga_menu")
        val receivedImage = intent.getStringExtra("image_menu")
        if(receivedImage != null){
            image_menu.setImageResource(receivedImage.toInt())
        }

        text_namaMenu.text = receivedName
        text_hargaV.text = "Rp. $receivedHarga"

        btn_saveOrder.setOnClickListener {
            if (et_jumlahOrder.text.isEmpty()){
                Toast.makeText(this, "Isi Jumlah Order", Toast.LENGTH_SHORT).show()
            }else{
                val Order_Model = Order_Menu_Model()
                Order_Model.str_Tgl         = text_tanggal.text.toString()
                Order_Model.str_Jam         = text_jam.text.toString()
                Order_Model.str_nmMenu      = text_namaMenu.text.toString()
                Order_Model.int_jmlOrder    = et_jumlahOrder.text.toString().toInt()
                Order_Model.int_hrgMenu     = text_hargaV.text.toString().toInt()

                MainActivity.dbHandler.addOrder(this, Order_Model)
            }
        }

        btn_orderLagi.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}