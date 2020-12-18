package com.example.nk2

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nk2.adapter.ShoppingCartAdapter
import com.example.nk2.model.Toko
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_shopping_cart.*

class ShoppingCartActivity : AppCompatActivity() {

    lateinit var adapter: ShoppingCartAdapter
    lateinit var url: String
    private var toko: Toko? = null

    companion object {
        const val EXTRA_TOKO = "extra_toko"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material)
        upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)

        toko = intent.getParcelableExtra(MahasiswaClickToko.EXTRA_TOKO)
        supportActionBar?.title = applicationContext.getString(R.string.mhs_keranjang) + " ${toko!!.NamaToko}"
        adapter = ShoppingCartAdapter(this, ShoppingCart.getCart())
        adapter.notifyDataSetChanged()

        shopping_cart_recyclerView.adapter = adapter
        shopping_cart_recyclerView.layoutManager = LinearLayoutManager(this)

        var totalPrice = ShoppingCart.getCart()
                .fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.hargaMenu!!.toDouble()) }

        btn_pesan.setOnClickListener {
            val fAuth = FirebaseAuth.getInstance()
            val fStore = FirebaseFirestore.getInstance()
            val whatsAppAppId = "com.whatsapp"
            try {
                // Jika WA tidak terinstall maka akan mengembalikan exception
                val packageManager = packageManager
                val info = packageManager.getPackageInfo(whatsAppAppId, PackageManager.GET_META_DATA)
                // Parsing mulai telp, namaToko, namaMenu, Qty dll.
                val number = toko!!.Telp
                Log.d("no telp = ", number.toString())
                val msgHeader = "text=Toko: ${toko!!.NamaToko}\n"
                var msgMenu = "Menu:\n"
                ShoppingCart.getCart().forEach {
                    msgMenu += "  - ${it.namaMenu}\t\tx${it.quantity}\n"
                }
                val msgHarga = "Total Harga: Rp. ${totalPrice.toLong()}\n"
                val user = fAuth.currentUser
                var parseMsg: String = ""
                fStore.collection("User").document(user!!.uid).get().addOnSuccessListener {
                    val msgNamaPemesan = "Atas Nama: ${it.getString("Nama")}"
                    parseMsg = "$msgHeader $msgMenu ${msgHarga} $msgNamaPemesan"
                    // parsing dari string ke url
                    url = "https://api.whatsapp.com/send?phone=$number&$parseMsg"
                    // mengirim pesan
                    sendMessage()
                }
            } catch (e: PackageManager.NameNotFoundException) {
                // Notifikasi WA tidak terpasang
                Toast.makeText(this, "WhatsApp is tidak terpasang pada device ini", Toast.LENGTH_LONG).show()
                // mengarahkan ke google play store
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$whatsAppAppId")))
            }
        }
        total_price.text = "Rp. ${totalPrice.toLong()}"
    }

    fun sendMessage() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }


}