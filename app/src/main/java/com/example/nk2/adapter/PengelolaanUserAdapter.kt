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
import com.example.nk2.AdminEditUser
import com.example.nk2.CustomOnItemClickListener
import com.example.nk2.R
import com.example.nk2.model.Mahasiswa
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.admin_listviewuser.view.*

//Users yang ada di BErandaAdapter diganti Beranda
class PengelolaanUserAdapter(private val Users: ArrayList<Mahasiswa>, val context: Context, val activity: Activity) : RecyclerView.Adapter<PengelolaanUserAdapter.Holder>() {
    val fStore = FirebaseFirestore.getInstance()

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: Mahasiswa) {
            with(itemView) {
                LV_NamaUser.text = model.nama
                LV_NIM.text = model.nim
                LV_EditUser.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        val item = Users.get(position)
                        val intent = Intent(context, AdminEditUser::class.java)
                        intent.putExtra("ID_User", item.id)
                        activity.startActivity(intent)
                    }
                }))
                LV_DeleteUser.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(view: View, position: Int) {
                        val item = Users.get(position)
                        val ID = item.id
                        val df = fStore.collection("User").document("$ID").delete()
                        Users.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(activity, context.getString(R.string.hapus_sukses), Toast.LENGTH_SHORT).show()
                    }
                }))

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.admin_listviewuser, parent, false)

        return Holder(v)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(Users[position])
    }

    override fun getItemCount(): Int = Users?.size
}