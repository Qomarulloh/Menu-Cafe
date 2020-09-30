package com.example.cafe

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import java.lang.Exception

class Order_Menu_DBHandler(context: Context, name:String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object{
        private val DATABASE_NAME = "Cafegaul.db"
        private val DATABASE_VERSION = 1

        val TABLE_NAME = "tblOrderMenu"
        private val colId = "Id"
        private val colTgl = "Tanggal"
        private val colJam = "Jam"
        private val colNamaMenu = "NamaMenu"
        private val colJmlOrder = "JmlOrder"
        private val colHargaMenu = "HargaMenu"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE : String = ("CREATE TABLE $TABLE_NAME (" +
                "$colId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$colTgl TEXT," +
                "$colJam TEXT," +
                "$colNamaMenu TEXT," +
                "$colJmlOrder INTEGER," +
                "$colHargaMenu INTEGER)")
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun addOrder(mCtx: Context, OrderModel : Order_Menu_Model){
        val Data_Values = ContentValues()
        Data_Values.put(colTgl, OrderModel.str_Tgl)
        Data_Values.put(colJam, OrderModel.str_Jam)
        Data_Values.put(colNamaMenu, OrderModel.str_nmMenu)
        Data_Values.put(colJmlOrder, OrderModel.int_jmlOrder)
        Data_Values.put(colHargaMenu, OrderModel.int_hrgMenu)

        val db = this.writableDatabase

        try {
            db.insert(TABLE_NAME, null, Data_Values)
            Toast.makeText(mCtx, "Pesan Terkirim", Toast.LENGTH_SHORT).show()
        }catch (e : Exception){
            Toast.makeText(mCtx, e.message, Toast.LENGTH_SHORT).show()
        }

        db.close()
    }

    fun getAllOrder(mCtx: Context): ArrayList<Order_Menu_Model>{
        val query = "Select * from $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        val rec_order = ArrayList<Order_Menu_Model>()
        if (cursor.count == 0){
            Toast.makeText(mCtx,"Tidak ada Data", Toast.LENGTH_SHORT).show()
        }else{
            cursor.moveToFirst()
            while (!cursor.isAfterLast()) {
                val Order = Order_Menu_Model()
                Order.str_id = cursor.getInt(cursor.getColumnIndex(colId))
                Order.str_Tgl = cursor.getString(cursor.getColumnIndex(colTgl))
                Order.str_Jam = cursor.getString(cursor.getColumnIndex(colJam))
                Order.str_nmMenu = cursor.getString(cursor.getColumnIndex(colNamaMenu))
                Order.int_jmlOrder = cursor.getInt(cursor.getColumnIndex(colJmlOrder))
                Order.int_hrgMenu = cursor.getInt(cursor.getColumnIndex(colHargaMenu))
                Order.int_totalHarga = Order.int_jmlOrder * Order.int_hrgMenu
                rec_order.add(Order)
                cursor.moveToNext()
            }
            Toast.makeText(mCtx, "${cursor.count}) pesanan ditemukan", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return rec_order
    }

    fun deleteOrder(OrderID: Int): Boolean{
        val query = "Delete from $TABLE_NAME where ${colId} = $OrderID"
        val db = this.writableDatabase
        var result : Boolean = false
        try {
            val cursor = db.execSQL(query)
            result = true
        } catch (e: Exception){
            Log.e(ContentValues.TAG, "Terjadi kesalahan penghapusan")
        }
        db.close()
        return result
    }
}