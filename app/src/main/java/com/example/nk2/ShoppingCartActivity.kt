package com.example.nk2

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.nk2.adapter.ShoppingCartAdapter
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.user_beranda.*

class ShoppingCartActivity : AppCompatActivity() {

    lateinit var adapter: ShoppingCartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material)
        upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)

        adapter = ShoppingCartAdapter(this, ShoppingCart.getCart())
        adapter.notifyDataSetChanged()

        shopping_cart_recyclerView.adapter = adapter

        shopping_cart_recyclerView.layoutManager = LinearLayoutManager(this)

        var totalPrice = ShoppingCart.getCart()
            .fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.hargaMenu!!.toDouble()) }

        btn_pesan.setOnClickListener {
            try {
                // todo parsing telp toko
                val whatsAppAppId = "com.whatsapp"
                val number = 62
                val msg = "text=Toko: MinumanPojok%0aMenu: Es Teh x1%0a - Aqua x2%0a Total Harga: Rp. 17500%0aAtas Nama: Apaini Iniapa"
                val packageManager = packageManager;
                val info = packageManager.getPackageInfo(whatsAppAppId, PackageManager.GET_META_DATA)
                val url = "https://api.whatsapp.com/send?phone=$number&$msg"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                //the following line will throw the NameNotFoundException if WhatsApp is not installed in the phone

            } catch (e: PackageManager.NameNotFoundException) {
                Toast.makeText(this, "WhatsApp is not installed in this device", Toast.LENGTH_LONG).show()
            }
        }
        total_price.text = "Rp. ${totalPrice}"
    }

}