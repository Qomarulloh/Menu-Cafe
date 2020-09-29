package com.example.cafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var dbHandler: Order_Menu_DBHandler
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = Order_Menu_DBHandler(this, null, null, 1)

        val menuCafe = ArrayList<Menu_Cafe_Model>()
        menuCafe.add(
            Menu_Cafe_Model(
                "Kopi Hitam",
                "Kopi hitam dibuat dengan teknik esspresso, " +
                        "dimana biji kopi yang digunakan berasal dari " +
                        "Aceh Gayo jenis Arabika, disajikan dengan " +
                        "gula terpisah.",
                10000,
                R.drawable.kopihitam
            )
        )

        menuCafe.add(
            Menu_Cafe_Model(
                "Cappucino",
                "Paduan kopi dengan buih susu kental serta " +
                        "menggunakan sirup karamel, dimana biji kopi " +
                        "yang digunakan berasal dari Gunung Puntang " +
                        "Jawa Barat jenis Robusta.",
                20000,
                R.drawable.cappucino
            )
        )

        menuCafe.add(
            Menu_Cafe_Model(
                "Sparkling Tea",
                "Minuman teh yang menggunakan daun teh dari " +
                        "pegunungan daerah garut dengan tambahan sari " +
                        "melati asli dan gula merah alami.",
                15000,
                R.drawable.sparklingtea
            )
        )

        menuCafe.add(
            Menu_Cafe_Model(
                "Batagor",
                "Baso dan tahu goreng dengan sajian bumbu kacang " +
                        "dan kecap khas Bandung.",
                25000,
                R.drawable.batagor
            )
        )

        menuCafe.add(
            Menu_Cafe_Model(
                "Cireng",
                "Makanan ringan berupa tepung kanji goreng isi bahan " +
                        "dasar tempe fermentasi yang disebut oncom, disajikan " +
                        "dengan bumbu kacang pedas.",
                10000,
                R.drawable.cireng
            )
        )

        menuCafe.add(
            Menu_Cafe_Model(
                "Nasi Goreng",
                "Nasi goreng ayam yang disajikan dengan telur mata " +
                        "sapi disertai satai ayam.",
                30000,
                R.drawable.nasigoreng
            )
        )

        menuCafe.add(
            Menu_Cafe_Model(
                "Cheese Cake",
                "Kue Tart 1 slice dengan bahan utama cream cheese " +
                        "dengan topping buah-buahan asli seperti Anggur, " +
                        "Jeruk dan Kiwi.",
                40000,
                R.drawable.chessecake
            )
        )

        menuCafe.add(
            Menu_Cafe_Model(
                "Black Salad",
                "Potongan buah-buah segar yang terdiri dari Pepaya, " +
                        "Jambu, Melon dan Mangga disajikan dengan bumbu rujak " +
                        "kacang pedas dan gula merah.",
                20000,
                R.drawable.blacksalad
            )
        )

        val object_adapter : Menu_Cafe_Adapter
        object_adapter = Menu_Cafe_Adapter(applicationContext, menuCafe)
        val menu = findViewById<ListView>(R.id.list_menu_cafe)
        menu.adapter = object_adapter

        list_menu_cafe.setOnItemClickListener()
        {
            adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            val intent = Intent(applicationContext, Order_Menu::class.java)
            intent.putExtra("nama_menu", menuCafe[position].str_nama.toString())
            intent.putExtra("harga_menu", menuCafe[position].int_harga.toString())
            startActivity(intent)
        }

        fabtogle.setOnClickListener{
            startActivity(Intent(this, View_Order::class.java))
        }
    }
}