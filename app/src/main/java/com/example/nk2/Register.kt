package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap

class Register:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        val registerNama = findViewById<EditText>(R.id.register_nama)
        val registerNim = findViewById<EditText>(R.id.register_nim)
        val registerEmail = findViewById<EditText>(R.id.register_email)
        val registerTelp = findViewById<EditText>(R.id.register_NomorTelp)
        val registerPass = findViewById<EditText>(R.id.register_password)
        val btnRegister = findViewById<Button>(R.id.register_btn_register)


        val fAuth = FirebaseAuth.getInstance()
        val fStore = FirebaseFirestore.getInstance()
        if(fAuth.currentUser != null){
            val intent = Intent (applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }

        btnRegister.setOnClickListener(View.OnClickListener {

                val email = registerEmail.getText().toString().trim()
                val password = registerPass.getText().toString().trim()
                val nama = registerNama.getText().toString()
                val nim = registerNim.getText().toString()
                val telp = registerTelp.getText().toString()



                if (TextUtils.isEmpty(email)){
                    registerEmail.setError("Email harus diisi")
                    return@OnClickListener
                }
                if (TextUtils.isEmpty(password)){
                    registerPass.setError("Password harus diisi")
                    return@OnClickListener
                }
                if(password.length < 6){
                    registerPass.setError("Password harus lebih dari 6 karakter")
                    return@OnClickListener
                }

                //Database
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show()

                        //taruh di Firestore
                        val user = fAuth.currentUser
                        val df = fStore.collection("User").document(user!!.uid)
                        val userInfo = hashMapOf(
                            "Nama" to nama,
                            "Nim" to nim,
                            "Telp" to telp,
                            "Email" to email,
                            "isUser" to "1",
                            "isAdmin" to "0")
                        df.set(userInfo)

                        val intent = Intent (applicationContext, Login::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else {
                        Toast.makeText(this,"Error !"+ it.exception!!.message , Toast.LENGTH_SHORT).show()
                    }
                }

        })
    }

    fun login(view: View) {
        val intent = Intent (applicationContext, Login::class.java)
        startActivity(intent)
        finish()
    }
}





