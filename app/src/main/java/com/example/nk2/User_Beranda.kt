package com.example.nk2

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.nk2.controller.MahasiswaController
import com.example.nk2.model.Mahasiswa
import com.example.nk2.model.Toko
import com.google.firebase.firestore.FirebaseFirestore
import com.google.protobuf.Empty

class User_Beranda: AppCompatActivity() {

    val fStore = FirebaseFirestore.getInstance()
    var Data : MutableList<Mahasiswa> = arrayListOf()
    var idToko = ""
    var desk = ""
    var nama = ""
    var menu = ""
    var telp = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_beranda)
        getAllToko()
    }

    fun getAllToko(){
        Data.clear()
        var tokoItem : Toko
        val df = fStore.collection("User").get().addOnSuccessListener { result ->
            for (document in result) {
//                idToko = document.id
//                nama = document.data.get("NamaToko").toString()
//                desk = document.data.get("Deskripsi").toString()
//                telp = document.data.get("Telp").toString()
//                menu = document.data.get("Menu").toString()
//                tokoItem = Toko(idToko, nama, desk, telp, menu)
                Log.d(ContentValues.TAG, "${document.id} => ${document.get("Email")}")
            }
        }.addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
        Log.d("Apaini = ", Data.toString())
    }
    fun beranda_profil(view: View) {
        val intent = Intent (applicationContext, User_Profil::class.java)
        startActivity(intent)
    }
}