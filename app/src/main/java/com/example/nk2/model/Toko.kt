package com.example.nk2.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Toko(var id: String, var NamaToko: String, var Deskripsi: String, var Telp: String, var Menu: String) : Parcelable