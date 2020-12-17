package com.example.nk2

import com.google.common.truth.Truth
import org.junit.Test

class AddItemUtilTest {
    @Test
    fun `sukses menambah pesanan setelah klik return true`() {
        val result = AddItemUtil.addItem(true, 1, "Nasi Goreng",5000, 2
        )
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `tidak menambah pesanan tidak klik return false`() {
        val result = AddItemUtil.addItem(false, 1, "Nasi Goreng",5000, 2
        )
        Truth.assertThat(result).isFalse()
    }
}