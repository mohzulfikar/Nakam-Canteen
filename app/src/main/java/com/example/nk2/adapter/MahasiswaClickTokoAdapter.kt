package com.example.nk2.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nk2.CustomOnItemClickListener
import com.example.nk2.R
import com.example.nk2.model.Menu
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
                    btn_add_jml_menu.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Log.d("***", "$position");

//                            val intent = Intent(context, UserClickToko::class.java)
//                            intent.putExtra(UserClickToko.EXTRA_POSITION, position)
//                            activity.startActivityForResult(intent, UserClickToko.VIEW_MENU)
                        }
                    }))
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