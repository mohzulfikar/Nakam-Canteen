package com.example.nk2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nk2.adapter.MahasiswaBerandaAdapter
import com.example.nk2.model.Toko
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.user_beranda.*
import java.util.*
import kotlin.collections.ArrayList

class MahasiswaBeranda : AppCompatActivity() {

    val fStore = FirebaseFirestore.getInstance()
    var Data: MutableList<Toko> = arrayListOf()
    var searchList: MutableList<Toko> = arrayListOf()
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
            for (document in it) {
                idToko = document.id
                nama = document.data["NamaToko"].toString()
                desk = document.data["Deskripsi"].toString()
                telp = document.data["Telp"].toString()
                menu = document.data["Menu"] as ArrayList<String>
                harga = document.data["Harga"] as ArrayList<Long>
                tokoItem = Toko(idToko, nama, desk, telp, menu, harga)
                Data.add(tokoItem)
//                Log.d(
//                        android.content.ContentValues.TAG,
//                        "${document.id} => $Data"
//                )
            }
            searchList.addAll(Data)
            val adapter = MahasiswaBerandaAdapter(searchList, this, this)
            adapter.notifyDataSetChanged()
            //tampilkan data dalam recycler view
            rc_card!!.adapter = adapter
//            Log.d("nilainya", Data.size.toString())
//            Log.d(
//                    android.content.ContentValues.TAG,
//                    "? => $Data"
//            )
        }
    }

    fun beranda_profil(view: View) {
        val intent = Intent(applicationContext, MahasiswaProfil::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.cari)
        if (menuItem != null) {
            val searchView = menuItem.actionView as SearchView
            searchView.queryHint = "Cari Toko..."
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(pencarian: String?): Boolean {
                    return searchMenu(pencarian)
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    fun searchMenu(namaMenu: String?): Boolean {
        if (namaMenu!!.isNotEmpty()) {
            searchList.clear()
            val pencarianQuery = namaMenu.toLowerCase(Locale.getDefault())
            Data.forEach {
                if (it.NamaToko.toLowerCase(Locale.getDefault())!!.contains(pencarianQuery)) {
                    searchList.add(it)
                }
            }
            if (searchList.size > 0) {
                Toast.makeText(this@MahasiswaBeranda, "Ditemukan ${searchList.size} Toko", Toast.LENGTH_SHORT).show()
                tv_hasil_cari.visibility = View.GONE
            } else {
                Toast.makeText(this@MahasiswaBeranda, "Toko Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                tv_hasil_cari.visibility = View.VISIBLE
            }
            rc_card.adapter!!.notifyDataSetChanged()
        } else {
            searchList.clear()
            searchList.addAll(Data)
            rc_card.adapter!!.notifyDataSetChanged()
            tv_hasil_cari.visibility = View.GONE
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}