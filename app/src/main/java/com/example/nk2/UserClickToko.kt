package com.example.nk2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nk2.adapter.BerandaAdapter
import com.example.nk2.adapter.UserClickTokoAdapter
import com.example.nk2.model.Menu
import com.example.nk2.model.Toko
import kotlinx.android.synthetic.main.user_beranda.*
import kotlinx.android.synthetic.main.user_click_toko.*

class UserClickToko : AppCompatActivity(){
    private var isView = false
    private var toko: Toko? = null
    private var menus: ArrayList<Menu> = arrayListOf()
    private var position: Int = 0
    companion object {
        const val EXTRA_TOKO = "extra_toko"
        const val EXTRA_POSITION = "extra_position"
        const val VIEW_MENU = 100
        const val EXTRA_MENU = "extra_menu"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_click_toko)
        toko = intent.getParcelableExtra(EXTRA_TOKO)
        if (toko != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isView = true
        } else {
            Log.d("Nilainya dari intent add ini masuk: ", toko.toString())
            toko = Toko()
        }
        // parsing ke objek menu
        val ukuran = toko!!.Menu.size
        for (i in 0 until ukuran){
            menus.add(Menu(toko!!.Menu[i], toko!!.Harga[i]))
        }
        supportActionBar?.title = applicationContext.getString(R.string.menu_toko)
        menu_rc_card.layoutManager = LinearLayoutManager(this)

        if (isView) {
            toko?.let {
                tv_toko_klik_nama.text = it.NamaToko
                tv_toko_klik_desc.text = it.Deskripsi
            }
            val adapter = UserClickTokoAdapter(menus, this, this)
            Log.d("menunya = ", menus.toString())
            adapter.notifyDataSetChanged()
            //tampilkan data dalam recycler view
            menu_rc_card!!.adapter = adapter
            Log.d("nilainy itua", menus.size.toString())
            Log.d(
                android.content.ContentValues.TAG,
                "? => $menus"
            )
        }
    }
}