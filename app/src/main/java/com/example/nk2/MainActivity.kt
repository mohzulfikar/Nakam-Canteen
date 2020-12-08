package com.example.nk2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun logout1(view: View) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent (applicationContext, Login::class.java)
        startActivity(intent)
        finish()
    }


}