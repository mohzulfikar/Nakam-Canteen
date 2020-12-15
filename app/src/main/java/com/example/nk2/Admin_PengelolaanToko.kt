package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Admin_PengelolaanToko: AppCompatActivity() {
        var arrayNamaToko = arrayListOf<String>()
        var arrayIdToko = arrayListOf<String>()
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_pengelolaantoko)
        val fStore = FirebaseFirestore.getInstance()

        var ListNamaToko: ListView? = null


//        val df = fStore.collection("Toko").get().addOnSuccessListener {
//            for(document in it) {
//                arrayNamaToko.add(document.id)
//                ListNamaToko = findViewById<View>(R.id.PT_view) as ListView
//                val arrayAdapter = ArrayAdapter(this, R.layout.admin_listview, R.id.LV_NamaToko, arrayNamaToko)
//                ListNamaToko!!.adapter = arrayAdapter
//
//            }
//        }

         val df = fStore.collection("Toko").get().addOnSuccessListener {
             for(document in it) {
                 arrayNamaToko.add(document.get("NamaToko").toString())
                 arrayIdToko.add(document.id).toString()

                 ListNamaToko = findViewById<View>(R.id.PT_view) as ListView
                 val AA_NamaToko = ArrayAdapter(this, R.layout.admin_listview, R.id.LV_NamaToko, arrayNamaToko)

                 ListNamaToko!!.adapter = AA_NamaToko

             }
         }

//        readData(fAuth,fStore)
    }

    fun LV_Edit(view: View) {
        val intent = Intent (applicationContext, Admin_EditToko::class.java)
        intent.putExtra("ID_Toko",arrayIdToko.get(0))
        startActivity(intent)


    }

//    fun LV_Edit(view: View) {
//        val intent = Intent (applicationContearrayNamaTokot, Login::class.java)
//        intent.putExtra("ID_Toko",arrayNamaToko)
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