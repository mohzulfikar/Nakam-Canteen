package com.example.nk2

import java.util.*

object SearchUtil {
    var Data = listOf<String>("Fikar", "Rafi", "Bumba", "Afifun", "Tokotos")
    var searchList: MutableList<String> = arrayListOf()

    // Method search toko with the logics
    fun searchToko(
            keyword: String
    ): Boolean {
        // Cek keyworad apakah kosong
        if (keyword!!.isNotEmpty()) {
            val pencarianQuery = keyword.toLowerCase(Locale.getDefault())
            Data.forEach {
                if (it.toLowerCase(Locale.getDefault())!!.contains(pencarianQuery)) {
                    searchList.add(it)
                }
            }
            return searchList.size > 0
        } else {
            return false
        }

    }
}