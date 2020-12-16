package com.example.nk2.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nk2.CustomOnItemClickListener
import com.example.nk2.R
import com.example.nk2.User_Profil
import com.example.nk2.model.Toko
import kotlinx.android.synthetic.main.user_beranda_toko_item.view.*

class BerandaAdapter(private val Tokos:MutableList<Toko>, val context: Context, val activity: Activity) : RecyclerView.Adapter<BerandaAdapter.Holder>(){
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(toko: Toko) {
            with(itemView){
                tv_toko_nama.text = toko.NamaToko
                tv_toko_desc.text = toko.Deskripsi
                Log.d("nilainyahhh", Tokos.size.toString())
                cardList.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        val item = Tokos.get(position)
                        var itemName = item.NamaToko
                        Log.d("***", "${position}");
                        val intent = Intent(context, User_Profil::class.java)
                        intent.putExtra("ID", item.id)
                        intent.putExtra("NAMA", item.NamaToko)
                        intent.putExtra("DESC", item.Deskripsi)
                        intent.putExtra("MENU", item.Menu)
                        context.startActivity(intent)
                    }
                }))
//                lbList.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
//                    override fun onItemClicked(view: View, position: Int) {
//                        val item = list.get(position)
//                        var itemName = item.name
//                        var itemJenis = item.jenis
//                        Log.d("***", "${position}");
//                        val intent = Intent(context, Coba::class.java)
//                        intent.putExtra("idUser", position)
//                        intent.putExtra("namaUser", itemName)
//                        intent.putExtra("jenisUser", itemJenis)
//                        context.startActivity(intent)
//                    }
//                }))
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BerandaAdapter.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.user_beranda_toko_item,parent,false))
    }

    override fun onBindViewHolder(holder: BerandaAdapter.Holder, position: Int) {
        holder.bind(Tokos[position])
    }

    override fun getItemCount(): Int = Tokos?.size


}