package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nk2.adapter.MahasiswaBerandaAdapter
import com.example.nk2.model.Toko
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.user_beranda.*

class MahasiswaBeranda : AppCompatActivity() {

    val fStore = FirebaseFirestore.getInstance()
    var Data: MutableList<Toko> = arrayListOf()
    var idToko = ""
    var desk = ""
    var nama = ""
    var telp = ""
    var menu = ArrayList<String>()
    var harga = ArrayList<Long>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_beranda)
        supportActionBar?.title = applicationContext.getString(R.string.beranda)
        rc_card.layoutManager = LinearLayoutManager(this)

        val df = fStore.collection("Toko").get().addOnSuccessListener {
            var tokoItem: Toko
            for(document in it) {
                idToko = document.id
                nama = document.data["NamaToko"].toString()
                desk = document.data["Deskripsi"].toString()
                telp = document.data["Telp"].toString()
                menu = document.data["Menu"] as ArrayList<String>
                harga = document.data["Harga"] as ArrayList<Long>
                tokoItem = Toko(idToko, nama, desk, telp, menu, harga)
                Data.add(tokoItem)
                Log.d(
                    android.content.ContentValues.TAG,
                    "${document.id} => $Data"
                )
            }
            val adapter = MahasiswaBerandaAdapter(Data, this, this)
            adapter.notifyDataSetChanged()
            //tampilkan data dalam recycler view
            rc_card!!.adapter = adapter
            Log.d("nilainya", Data.size.toString())
            Log.d(
                android.content.ContentValues.TAG,
                "? => $Data"
            )
        }
//        callbackToko : MyCallback
//        getToko(object: MyCallback {
//            override fun onCallback(value: MutableList<Toko>) {
//                Log.d("nilainya", Data.size.toString())
//            }
//        })
    }

    fun beranda_profil(view: View) {
        val intent = Intent(applicationContext, MahasiswaProfil::class.java)
        startActivity(intent)
    }

//    override fun onDataChange(){
//
//    }
//
//    suspend fun getTokos() {
//        var Data = getAllTokoFirestore().toObject(Toko::class.java)
//    }

//    suspend fun getAllTokoFirestore(): DocumentSnapshot {
//        return try {
//            val data = fStore
//                .collection("users")
//                .document("Toko")
//                .get()
//                .await()
//            data
//        } catch (e: Exception) {
//            Log.d("Galat", "Galat Mengambil Data")
//            null!!
//        }
////        df. .addOnCompleteListener {
////            if (it.isSuccessful) {
////                var tokoItem : Toko
////                Data.clear()
////                for (document in it.result!!) {
////                    idToko = document.id
////                    nama = document.data["NamaToko"].toString()
////                    desk = document.data["Deskripsi"].toString()
////                    telp = document.data["Telp"].toString()
////                    menu = document.data["Menu"].toString()
////                    tokoItem = com.example.nk2.model.Toko(idToko, nama, desk, telp, menu)
////                    Data.add(tokoItem)
////                    android.util.Log.d(
////                        android.content.ContentValues.TAG,
////                        "${document.id} => ${Data}"
////                    )
////                }
////            }
//    }
//}


//    fun getToko(myCallback: MyCallback) {
//        fStore.collection("Toko").get().addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val list = mutableListOf<Toko>()
//                var tokoItem: Toko
//                Data.clear()
//
////                val types: List<Toko> = documentSnapshots.toObjects(Toko::class.java)
//                for (document in task.result!!) {
//                    val name = document.data["name"].toString()
//                    idToko = document.id
//                    nama = document.data["NamaToko"].toString()
//                    desk = document.data["Deskripsi"].toString()
//                    telp = document.data["Telp"].toString()
//                    menu = document.data["Menu"].toString()
//                    tokoItem = com.example.nk2.model.Toko(idToko, nama, desk, telp, menu)
//                    list.add(tokoItem)
//
//                }
//                myCallback.onCallback(list)
//            }
//        }
//    }
//    interface MyCallback {
//        fun onCallback(value: MutableList<Toko>)
//    }
}