package com.example.cafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe.MainActivity.Companion.dbHandler

class View_Order : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view__order)

        viewOrder()
    }

    private fun viewOrder(){
        val vieworderlist = dbHandler.getAllOrder(this)
        val adapter = View_Order_Adapter(this, vieworderlist)
        val rv: RecyclerView = findViewById(R.id.rv_order)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.adapter = adapter
    }
}