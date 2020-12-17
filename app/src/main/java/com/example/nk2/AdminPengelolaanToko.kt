package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nk2.adapter.PengelolaanTokoAdapter
import com.example.nk2.model.Toko
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.admin_pengelolaantoko.*
import java.util.*
import kotlin.collections.ArrayList

class AdminPengelolaanToko: AppCompatActivity() {
        var ListToko = ArrayList<Toko>()
        val fStore = FirebaseFirestore.getInstance()
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_pengelolaantoko)
         supportActionBar?.title = applicationContext.getString(R.string.pengelolaan_toko)

         RC_toko.layoutManager = LinearLayoutManager(this)

         val df = fStore.collection("Toko").get().addOnSuccessListener {
             for(document in it) {
                 val IdToko = document.id.toString()
                 val NamaToko = document.get("NamaToko").toString()
                 val Deskripsi = document.get("Deskripsi").toString()
                 val Telp = document.get("Telp").toString()
                 val Menu = document.get("Menu").toString()


                 ListToko.add(Toko("$IdToko","$NamaToko","$Deskripsi","$Telp", arrayListOf(), arrayListOf()))

             }
             val PengelolaanTokoAdapter = PengelolaanTokoAdapter(ListToko,this, this)
             PengelolaanTokoAdapter.notifyDataSetChanged()
             RC_toko.adapter = PengelolaanTokoAdapter
         }

}

    fun TambahToko(view: View) {
        val intent = Intent(applicationContext, AdminRegisterToko::class.java)
//        intent.putExtra("TambahToko", 200)
        startActivity(intent)
    }
}