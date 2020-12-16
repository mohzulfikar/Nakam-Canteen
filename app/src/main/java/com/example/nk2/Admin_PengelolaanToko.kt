package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nk2.adapter.PengelolaanTokoAdapter
import com.example.nk2.model.Toko
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.admin_pengelolaantoko.*

class Admin_PengelolaanToko: AppCompatActivity() {
        var ListToko = ArrayList<Toko>()
        var arrayIdToko = arrayListOf<String>()
        val fStore = FirebaseFirestore.getInstance()
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_pengelolaantoko)



//        val df = fStore.collection("Toko").get().addOnSuccessListener {
//            for(document in it) {
//                arrayNamaToko.add(document.id)
//                ListNamaToko = findViewById<View>(R.id.PT_view) as ListView
//                val arrayAdapter = ArrayAdapter(this, R.layout.admin_listview, R.id.LV_NamaToko, arrayNamaToko)
//                ListNamaToko!!.adapter = arrayAdapter
//
//            }
//        }
         RC_toko.layoutManager = LinearLayoutManager(this)

         val df = fStore.collection("Toko").get().addOnSuccessListener {
             for(document in it) {
//                 Deskripsi: String, var Telp: String, var Menu: String
                 val IdToko = document.id.toString()
                 val NamaToko = document.get("NamaToko").toString()
                 val Deskripsi = document.get("Deskripsi").toString()
                 val Telp = document.get("Telp").toString()
                 ListToko.add(Toko("$IdToko","$NamaToko","$Deskripsi","$Telp", arrayListOf(), arrayListOf()))

//                 arrayIdToko.add(IdToko)
                 Log.d("TAG_ID_Toko",IdToko)
             }
             val PengelolaanTokoAdapter = PengelolaanTokoAdapter(ListToko,this, this)
             PengelolaanTokoAdapter.notifyDataSetChanged()
             RC_toko.adapter = PengelolaanTokoAdapter
         }


//        readData(fAuth,fStore)
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