package com.example.nk2

object AddItemUtil {
    val item = listOf<String>()
    var clicked = true

    fun addItem(
            clicked: Boolean,
            id: Int,
            namaMenu: String = "",
            hargaMenu: Long = 0,
            quantity: Int = 0
    ): Boolean {
        var cnt = item.size
        val targetItem = item.size

        if (clicked == true) {
            if (targetItem == 0) {
                cnt += quantity
            } else {
                cnt += quantity
            }

            return cnt == item.size + quantity
        } else {
            return false
        }
    }
}