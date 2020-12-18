package com.example.nk2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mahasiswa(
        var id: String,
        var nama: String,
        var nim: String,
        var telp: String,
        var email: String
) : Parcelable