package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.admin_edit_toko.*


class AdminEditToko: AppCompatActivity() {
    val fStore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_edit_toko)
        supportActionBar?.title = applicationContext.getString(R.string.edit_toko)
        val HeaderNamaToko = findViewById<TextView>(R.id.ET_HEADERnamaToko);
        val NamaToko = findViewById<TextView>(R.id.ET_namaToko);
        val Telepon = ET_Telp
        val Menu1= findViewById<EditText>(R.id.ET_Menu1);
        val Menu2 = findViewById<EditText>(R.id.ET_Menu2)
        val Harga1 = ET_HargaMenu1
        val Harga2 = ET_HargaMenu2
        val Deskripsi = findViewById<EditText>(R.id.ET_deskripsiToko);

        val IdToko = intent.getStringExtra("ID_Toko")

        val df = fStore.collection("Toko").document(IdToko.toString())
        df.get().addOnSuccessListener {
//            var a = it.get("Nama").toList()
             var Menu = it.data!!.get("Menu") as ArrayList<String>
             var Harga = it.data!!.get("Harga") as ArrayList<Long>
            if (IdToko != null){
                HeaderNamaToko.setText(it.getString("NamaToko"))
                NamaToko.setText(it.getString("NamaToko"))
                Deskripsi.setText(it.getString("Deskripsi"))
                Menu1.setText(Menu[0])
                Menu2.setText(Menu[1])
                Harga1.setText(Harga[0].toString())
                Harga2.setText(Harga[1].toString())
                Telepon.setText(it.getString("Telp"))
                Log.d("TAG_ID_Menu", Harga[0].toString())
                Log.d("TAG_ID_Menu", Harga[1].toString())
            }
        }


        ET_Update.setOnClickListener {
            var menu= listOf<String>(Menu1.text.toString(),Menu2.text.toString())
            var harga= listOf(Harga1.text.toString().toLong(), Harga2.text.toString().toLong() )
            df.update("Deskripsi", "${Deskripsi.text}")
            df.update("NamaToko", "${NamaToko.text}")
            df.update("Menu", menu)
            df.update("Harga", harga)
            Toast.makeText(this,HeaderNamaToko.text, Toast.LENGTH_SHORT).show()
            val intent = Intent(applicationContext, AdminPengelolaanToko::class.java)
            startActivity(intent)
            finish()
        }

    }

}
