package com.example.cafe

class Menu_Cafe_Model {
    var str_nama: String = ""
    var str_des: String = ""
    var int_harga: Int = 0
    var int_image: Int = 0
    constructor(str_nama: String, str_des: String,int_harga: Int, int_image: Int){
        this.str_nama = str_nama
        this.str_des = str_des
        this.int_harga = int_harga
        this.int_image = int_image
    }
}