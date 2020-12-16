package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nk2.adapter.BerandaAdapter
import com.example.nk2.model.Toko
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.user_beranda.*
import kotlinx.coroutines.tasks.await

class User_Beranda : AppCompatActivity() {

    val fStore = FirebaseFirestore.getInstance()
    var Data: MutableList<Toko> = arrayListOf()
    var idToko = ""
    var desk = ""
    var nama = ""
    var menu = ""
    var telp = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_beranda)

        rc_card.layoutManager = LinearLayoutManager(this)

        val df = fStore.collection("Toko").get().addOnSuccessListener {
            var tokoItem: Toko
            for(document in it) {
                val name = document.data["name"].toString()
                idToko = document.id
                nama = document.data["NamaToko"].toString()
                desk = document.data["Deskripsi"].toString()
                telp = document.data["Telp"].toString()
                menu = document.data["Menu"].toString()
                tokoItem = Toko(idToko, nama, desk, telp, menu)
                Data.add(tokoItem)
//                Log.d(
//                    android.content.ContentValues.TAG,
//                    "${document.id} => ${Data}"
//                )
            }
            val adapter = BerandaAdapter(Data, this, this)
            adapter.notifyDataSetChanged()
            //tampilkan data dalam recycler view
            rc_card!!.adapter = adapter
            Log.d("nilainya", Data.size.toString())
        }
//        callbackToko : MyCallback
//        getToko(object: MyCallback {
//            override fun onCallback(value: MutableList<Toko>) {
//                Log.d("nilainya", Data.size.toString())
//            }
//        })
    }

    fun beranda_profil(view: View) {
        val intent = Intent(applicationContext, User_Profil::class.java)
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