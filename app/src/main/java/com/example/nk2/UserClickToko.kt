package com.example.nk2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.nk2.model.Toko
import kotlinx.android.synthetic.main.user_click_toko.*

class UserClickToko : AppCompatActivity(){
    private var isView = false
    private var toko: Toko? = null
    private var position: Int = 0
    companion object {
        const val EXTRA_TOKO = "extra_toko"
        const val EXTRA_POSITION = "extra_position"
        const val VIEW_MENU = 100
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
            toko = Toko("", "", "", "", "")
        }
//        val actionBarTitle: String
//        val btnTitle: String
        supportActionBar?.title = applicationContext.getString(R.string.menu_toko)
        if (isView) {
            toko?.let {
                tv_toko_klik_nama.text = it.NamaToko
                tv_toko_klik_desc.text = it.Deskripsi
            }
        }
    }
}