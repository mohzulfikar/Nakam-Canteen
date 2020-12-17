package com.example.nk2

object RemoveItemUtil {
    // Ubah sesuai testing
    val item = listOf<String>()

    var clicked = true

    fun removeItem(
            clicked: Boolean,
            id: Int,
            namaMenu: String = "",
            hargaMenu: Long = 0,
            quantity: Int = 0
    ): Boolean {
        var cnt = item.size
        val targetItem = item.size

        if(clicked == true){
            if (targetItem == 0) {
                return true
            } else {
                cnt -= 1
            }

            if (cnt == item.size - 1){
                return true
            } else {
                return false
            }
        } else {
            return false
        }
    }
}