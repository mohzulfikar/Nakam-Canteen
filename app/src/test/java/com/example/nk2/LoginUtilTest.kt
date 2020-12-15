package com.example.nk2

import com.google.common.truth.Truth
import org.junit.Test

class LoginUtilTest {
    @Test
    fun `max attempt is 3 return false`() {
        val result = LoginUtil.validateLoginInput(
            "rafi@ub.ac.id",
            "190j123gj1921jf9j",
            3
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `email and password are valid return true`() {
        val result = LoginUtil.validateLoginInput(
            "gedelixa@ub.ac.id",
            "password123",
            1
        )
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `email and password are wrong return false`() {
        val result = LoginUtil.validateLoginInput(
            "gedelixa@ub.ac.id",
            "password1235",
            2
        )
        Truth.assertThat(result).isFalse()
    }
}