package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.nk2.adapter.PengelolaanUserAdapter
import com.example.nk2.model.Mahasiswa

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.admin_pengelolaanuser.*

class AdminPengelolaanUser : AppCompatActivity() {
    var ListUser = ArrayList<Mahasiswa>()
    var arrayIdToko = arrayListOf<String>()
    val fStore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_pengelolaanuser)
        supportActionBar?.title = applicationContext.getString(R.string.pengelolaan_user)

        RC_user.layoutManager = LinearLayoutManager(this)

        val df = fStore.collection("User").get().addOnSuccessListener {
            for(document in it) {
                val IdUser = document.id
                val NamaUser = document.get("Nama").toString()
                val NimUser = document.get("Nim").toString()
                val TelpUser = document.get("Telp").toString()
                val Email = document.get("Email").toString()
                ListUser.add(Mahasiswa("$IdUser","$NamaUser","$NimUser","$TelpUser", "$Email"))
                Log.d("TAG_ID_USER",IdUser.toString())
            }
            val PengelolaanUserAdapter = PengelolaanUserAdapter(ListUser,this, this)
            PengelolaanUserAdapter.notifyDataSetChanged()
            RC_user.adapter = PengelolaanUserAdapter
        }



    }

    fun RegisUser(view: View) {
        val intent = Intent (applicationContext, Register::class.java)
        startActivity(intent)
        finish()
    }
}