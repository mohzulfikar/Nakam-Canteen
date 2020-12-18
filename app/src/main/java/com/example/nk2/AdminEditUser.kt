package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AdminEditUser : AppCompatActivity() {
    val fStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_edit_user)
        supportActionBar?.title = applicationContext.getString(R.string.edit_User)
        val HeaderNamaUser = findViewById<TextView>(R.id.ET_HEADERnamaUser)
        val NamaUser = findViewById<TextView>(R.id.ET_namaUser)
        val NIM = findViewById<EditText>(R.id.ET_nim)
        val TELP = findViewById<EditText>(R.id.ET_telp)
        val Email = findViewById<EditText>(R.id.ET_email)

        val IdUser = intent.getStringExtra("ID_User")

        val df = fStore.collection("User").document(IdUser.toString())
        df.get().addOnSuccessListener {
            if (IdUser != null) {

                HeaderNamaUser.setText(it.getString("Nama"))
                NamaUser.setText(it.getString("Nama"))
                NIM.setText(it.getString("Nim"))
                TELP.setText(it.getString("Telp"))
                Email.setText(it.getString("Email"))
            }
        }

        findViewById<Button>(R.id.ET_Update).setOnClickListener {
            df.update("Nama", "${NamaUser.text}")
            df.update("Nim", "${NIM.text}")
            df.update("Telp", "${TELP.text}")
            df.update("Email", "${Email.text}")
            Toast.makeText(this, HeaderNamaUser.text, Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, AdminPengelolaanUser::class.java)
            startActivity(intent)
        }

    }

}
