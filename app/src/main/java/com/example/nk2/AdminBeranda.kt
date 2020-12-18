package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AdminBeranda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_beranda)
        supportActionBar?.title = applicationContext.getString(R.string.beranda)
//        val fStore = FirebaseFirestore.getInstance()
//        val df = fStore.collection("Toko").document("3")
////        var id: String, var NamaToko: String, var Deskripsi: String, var Telp: String, var Menu: String
//        val data = hashMapOf(
//                "NamaToko" to "TaTel FILKOM",
//                "Deskripsi" to "Spesialis Tahu Telur Khas Malang",
//                "Telp" to "6289521430402",
//                "Menu" to listOf("Tahu Lontong", "Tahu Telur Lontong", "Tahu Sutra Telur Spesial", "Extra Telur"),
//                "Harga" to listOf(8000, 9000, 12000, 3500))
//        df.set(data)
    }


    fun LOGOUT(view: View) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(applicationContext, Login::class.java)
        startActivity(intent)
        finish()
    }

    fun Btn_adminEditUser(view: View) {
        val intent = Intent(applicationContext, AdminPengelolaanUser::class.java)
        startActivity(intent)
        finish()
    }

    fun Btn_adminEditToko(view: View) {
        val intent = Intent(applicationContext, AdminPengelolaanToko::class.java)
        startActivity(intent)
        finish()
    }
}