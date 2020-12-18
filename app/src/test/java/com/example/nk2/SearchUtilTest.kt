package com.example.nk2

import com.google.common.truth.Truth
import org.junit.Test

class SearchUtilTest {
    @Test
    fun `keyword masih kosong return false`() {
        val result = SearchUtil.searchToko(
                ""
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `pencarian berhasil return true`() {
        val result = SearchUtil.searchToko(
                "Toko"
        )
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `pencarian tidak berhasil karena tidak ada nama toko return false`() {
        val result = SearchUtil.searchToko(
                "wiongiwhjwegiwjwg0g"
        )
        Truth.assertThat(result).isFalse()
    }
}