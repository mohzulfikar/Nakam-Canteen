package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Admin_Beranda: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_beranda)


    }
    fun Btn_adminEdit  (view: View) {
        val intent = Intent (applicationContext, Admin_PengelolaanToko::class.java)
        startActivity(intent)
        finish()
    }
}