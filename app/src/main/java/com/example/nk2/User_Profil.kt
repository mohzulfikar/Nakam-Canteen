package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class User_Profil: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profil)

        val fAuth = FirebaseAuth.getInstance()
        val fStore = FirebaseFirestore.getInstance()

        val user = fAuth.currentUser
        val df = fStore.collection("User").document(user!!.uid)

        df.get().addOnSuccessListener {
            findViewById<TextView>(R.id.profil_nama).setText(it.getString("Nama"))
            findViewById<TextView>(R.id.profil_nim).setText(it.getString("Nim"))
            findViewById<TextView>(R.id.profil_email).setText(it.getString("Email"))
        }




    }

    fun profil_logout(view: View) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent (applicationContext, Login::class.java)
        startActivity(intent)
        finish()
    }


}