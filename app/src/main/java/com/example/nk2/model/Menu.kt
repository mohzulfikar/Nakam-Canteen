package com.example.nk2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu(
        val id: Int,
        val namaMenu: String = "",
        val hargaMenu: Long = 0
) : Parcelable
