package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class Admin_Beranda: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_beranda)

        val fStore = FirebaseFirestore.getInstance()
        val df = fStore.collection("Toko").document("3")
//        var id: String, var NamaToko: String, var Deskripsi: String, var Telp: String, var Menu: String
        val data = hashMapOf(
                "NamaToko" to "Contoh",
                "Deskripsi" to "Memakai",
                "Telp" to "Array@",
                "Menu" to listOf("Menu", "Pada", "Doc", "Toko"),
                "Harga" to listOf(1, 2, 3, 4))
        df.set(data)
    }
    fun Btn_adminEdit  (view: View) {
        val intent = Intent (applicationContext, Admin_PengelolaanToko::class.java)
        startActivity(intent)
        finish()
    }
}