package com.example.nk2.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nk2.*
import com.example.nk2.model.Menu
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.user_click_toko.*
import kotlinx.android.synthetic.main.user_click_toko_item.view.*

class MahasiswaClickTokoAdapter (private val Menus:MutableList<Menu>, val context: Context, val activity: Activity) : RecyclerView.Adapter<MahasiswaClickTokoAdapter.Holder>(){
        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(menu: Menu) {
                with(itemView){
                    Log.d("nilai menu2 = ", menu.toString())
                    tv_menu_nama.text = menu.namaMenu
                    tv_menu_harga.text = menu.hargaMenu.toString()
                    Log.d("nilainyahhh", Menus.size.toString())
                    // todo jika klik akan nambah menu ke keranjang...

                    Observable.create(ObservableOnSubscribe<MutableList<CartItem>> {

                        btn_add_jml_menu.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                            override fun onItemClicked(view: View, position: Int) {
                                Log.d("***", "$position");
                                val item = CartItem(menu.id, menu.namaMenu, menu.hargaMenu)

                                ShoppingCart.addItem(item)
                                //notify users
                                Snackbar.make(
                                    (itemView.context as MahasiswaClickToko).coordinator,
                                    "${menu.namaMenu} added to your cart",
                                    Snackbar.LENGTH_LONG
                                ).show()
                                it.onNext(ShoppingCart.getCart())
                            }

                        }))


                        btn_rmv_jml_menu.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                            override fun onItemClicked(view: View, position: Int) {
                                Log.d("***", "$position");
                                val item = CartItem(menu.id, menu.namaMenu, menu.hargaMenu)

                                ShoppingCart.removeItem(item, itemView.context)

                                Snackbar.make(
                                    (itemView.context as MahasiswaClickToko).coordinator,
                                    "${menu.namaMenu} removed from your cart",
                                    Snackbar.LENGTH_LONG
                                ).show()
                                it.onNext(ShoppingCart.getCart())
                            }
                        }))



                    }).subscribe { cart ->
                        var quantity = 0

                        cart.forEach { cartItem ->
                            quantity += cartItem.quantity
                        }

                        (itemView.context as MahasiswaClickToko).cart_size.text = quantity.toString()
                        //Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
                    }


                }
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaClickTokoAdapter.Holder {
            return Holder(LayoutInflater.from(parent.context).inflate(R.layout.user_click_toko_item,parent,false))
        }

        override fun onBindViewHolder(holder: MahasiswaClickTokoAdapter.Holder, position: Int) {
            holder.bind(Menus[position])
        }

        override fun getItemCount(): Int = Menus?.size

}