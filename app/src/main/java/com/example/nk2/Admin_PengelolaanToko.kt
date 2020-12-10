package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Admin_PengelolaanToko: AppCompatActivity() {
        var x = arrayListOf<String>()
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_pengelolaantoko)
        val fStore = FirebaseFirestore.getInstance()
        val fAuth = FirebaseAuth.getInstance()
        var PTList: ListView? = null

        val df = fStore.collection("User").get().addOnSuccessListener {

            for(document in it) {
                x.add(document.id)
                PTList = findViewById<View>(R.id.PT_view) as ListView
                val arrayAdapter = ArrayAdapter(this, R.layout.admin_listview, R.id.LV_NamaToko, x)
                PTList!!.adapter = arrayAdapter

            }
        }

//        readData(fAuth,fStore)
    }

//    fun LV_Edit(view: View) {
//        val intent = Intent (applicationContext, Login::class.java)
//        intent.putExtra("ID_Toko",x)
//        startActivity(intent)
//
//    }


//    private fun readData(fAuth: FirebaseAuth,fStore: FirebaseFirestore) {
//        val user = fAuth.currentUser
//        val df = fStore.collection("User").document(user!!.uid)
//        df.get().getResult()?.getString("isAdmin")
//        df.get().addOnSuccessListener {
//            Log.d("TAG","Data Didapat: "+ it.getData())
//
//            if(it.getString("isAdmin") != null){
//                it.getString("Email")
//            }else if(it.getString("isUser") != null){
//                startActivity(Intent (applicationContext, Beranda_User::class.java))
//            }
//        }.addOnFailureListener{
//            Toast.makeText(this,"Akun tidak ada", Toast.LENGTH_SHORT).show()
//        }
//
//




}