package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AdminEditToko: AppCompatActivity() {
    val fStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_edit_toko)
        supportActionBar?.title = applicationContext.getString(R.string.edit_toko)
        val HeaderNamaToko = findViewById<TextView>(R.id.ET_HEADERnamaToko);
        val NamaToko = findViewById<TextView>(R.id.ET_namaToko);
        val Menu1= findViewById<EditText>(R.id.ET_Menu1);
        val Menu2 = findViewById<EditText>(R.id.ET_Menu2);
        val Deskripsi = findViewById<EditText>(R.id.ET_deskripsiToko);

        val IdToko = intent.getStringExtra("ID_Toko")

        val df = fStore.collection("Toko").document(IdToko.toString())
        df.get().addOnSuccessListener {
            if (IdToko != null){

                HeaderNamaToko.setText(it.getString("NamaToko"))
                NamaToko.setText(it.getString("NamaToko"))
//                Menu1.setText(it.getString("1"))
//                Menu2.setText(it.getString("2"))
                Deskripsi.setText(it.getString("Deskripsi"))
            }
        }

//        val TokoInfo = hashMapOf(
//            "NamaToko" to NamaToko.text,
//            "Deskripsi" to Deskripsi.text,
//            "1" to Menu1.text,
//            "2" to Menu2.text)

        findViewById<Button>(R.id.ET_Update).setOnClickListener {
            df.update("Deskripsi", "${Deskripsi.text}")
            Toast.makeText(this,HeaderNamaToko.text, Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, AdminPengelolaanToko::class.java)
            startActivity(intent)
        }

    }

}
