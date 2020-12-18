package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.admin_register_toko.*

class AdminRegisterToko : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_register_toko)
        supportActionBar?.title = applicationContext.getString(R.string.register_toko)
        val fStore = FirebaseFirestore.getInstance()

        val df = fStore.collection("Toko").document()
        Regis_Create.setOnClickListener {
            val NamaToko = Regis_namaToko.text.toString()
            val TelpToko = Regis_Telp.text.toString()
            val Menu1 = Regis_Menu1.text.toString()
            val Menu2 = Regis_Menu2.text.toString()
            val Harga1 = Regis_HargaMenu1.text.to(Long)
            val Harga2 = Regis_HargaMenu2.text.to(Long)
            val Deskripsi = Regis_deskripsiToko.text.toString()
            Log.d("TAG_ID_Toko", NamaToko)
            Log.d("TAG_ID_Deskripsi", Deskripsi)
            val data = hashMapOf(
                    "NamaToko" to NamaToko,
                    "Deskripsi" to Deskripsi,
                    "Telp" to TelpToko,
                    "Menu" to listOf(Menu1, Menu2),
                    "Harga" to listOf(Harga1, Harga2))
            df.set(data)

            val intent = Intent(applicationContext, AdminPengelolaanToko::class.java)
            startActivity(intent)
            finish()
        }


//            val x = fStore.collection("Toko").document()
////            var menu = arrayOf<String>(Menu1.text.toString(), Menu2.text.toString())
////            var harga = arrayOf<Long>(Harga1.text.toString().toLong(), Harga2.text.toString().toLong())
//            val userInfo = hashMapOf(
//                    "NamaToko" to NamaToko.text,
//                    "Deskripsi" to Deskripsi.text
////                    "Menu" to menu,
////                    "Harga" to harga
//            )
//            x.set(userInfo)
//            val intent = Intent(applicationContext, AdminPengelolaanToko::class.java)
//            startActivity(intent)
//            finish()
    }
}
