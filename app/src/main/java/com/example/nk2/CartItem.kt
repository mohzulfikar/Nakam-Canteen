package com.example.nk2

import com.example.nk2.model.Menu

data class CartItem(val id: Int,
                    var namaMenu: String = "",
                    var hargaMenu: Long = 0,
                    var quantity: Int = 0)