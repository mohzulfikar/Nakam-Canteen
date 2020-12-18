package com.example.nk2

import com.google.common.truth.Truth
import org.junit.Test

class RemoveItemUtilTest {
    // Tambahkan beberapa list terlebih dahulu pada RemoveItemUtil untuk test ini
    @Test
    fun `sukses menghapus pesanan setelah klik return true`() {
        val result = RemoveItemUtil.removeItem(true, 1, "Nasi Goreng", 5000, 1
        )
        Truth.assertThat(result).isTrue()
    }

    // Biarkan list kosong untuk test ini
    @Test
    fun `tidak menghapus pesanan setelah klik item 0 return true`() {
        val result = RemoveItemUtil.removeItem(true, 1, "Nasi Goreng", 5000, 1
        )
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `tidak menghapus pesanan tidak klik return true`() {
        val result = RemoveItemUtil.removeItem(false, 1, "Nasi Goreng", 5000, 1
        )
        Truth.assertThat(result).isFalse()
    }
}