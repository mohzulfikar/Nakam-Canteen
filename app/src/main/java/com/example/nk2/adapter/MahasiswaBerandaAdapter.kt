package com.example.nk2.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nk2.CustomOnItemClickListener
import com.example.nk2.MahasiswaClickToko
import com.example.nk2.R
import com.example.nk2.model.Toko
import kotlinx.android.synthetic.main.user_beranda_toko_item.view.*

class MahasiswaBerandaAdapter(private val Tokos: MutableList<Toko>, val context: Context, val activity: Activity) : RecyclerView.Adapter<MahasiswaBerandaAdapter.Holder>() {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(toko: Toko) {
            with(itemView) {
                tv_toko_nama!!.text = toko.NamaToko
                tv_toko_desc!!.text = toko.Deskripsi
                cardList.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        val item = Tokos.get(position)
                        var itemName = item.NamaToko
                        val intent = Intent(context, MahasiswaClickToko::class.java)
                        intent.putExtra(MahasiswaClickToko.EXTRA_POSITION, position)
                        intent.putExtra(MahasiswaClickToko.EXTRA_TOKO, toko)
                        activity.startActivityForResult(intent, MahasiswaClickToko.VIEW_MENU)
                    }
                }))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaBerandaAdapter.Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.user_beranda_toko_item, parent, false))
    }

    override fun onBindViewHolder(holder: MahasiswaBerandaAdapter.Holder, position: Int) {
        holder.bind(Tokos[position])
    }

    override fun getItemCount(): Int = Tokos?.size


}