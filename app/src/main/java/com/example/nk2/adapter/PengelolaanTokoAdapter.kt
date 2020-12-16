package com.example.nk2.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nk2.AdminEditToko
import com.example.nk2.CustomOnItemClickListener
import com.example.nk2.R
import com.example.nk2.model.Toko
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.admin_listview.view.*

//Tokos yang ada di BErandaAdapter diganti Beranda
class PengelolaanTokoAdapter(private val Tokos:ArrayList<Toko>,val context: Context,val activity: Activity): RecyclerView.Adapter<PengelolaanTokoAdapter.Holder>() {
    val fStore = FirebaseFirestore.getInstance()
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: Toko) {
            with(itemView) {
                LV_NamaToko.text = model.NamaToko
                LV_Tentang.text = model.Deskripsi
                LV_Edit.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        val item= Tokos.get(position)
                        val intent = Intent(context, AdminEditToko::class.java)
                        intent.putExtra("ID_Toko", item.id)
                        activity.startActivity(intent)
                    }
                }))
                LV_Delete.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        val item= Tokos.get(position)
                        val ID= item.id
                        val df = fStore.collection("Toko").document("$ID").delete()
                        Log.d("TAG Position:",ID)
                        Tokos.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(activity, context.getString(R.string.hapus_sukses), Toast.LENGTH_SHORT).show()
                    }
                }))

            }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.admin_listview,parent,false)

            return Holder(v)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(Tokos[position])
        }

        override fun getItemCount(): Int = Tokos?.size
}