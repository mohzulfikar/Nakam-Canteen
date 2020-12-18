package com.example.nk2

import com.google.common.truth.Truth
import org.junit.Test

class LoginUtilTest {
    @Test
    fun `max attempt is 3 return false`() {
        val result = LoginUtil.validateLoginInput(
                "gedelixa@ub.ac.id",
                "password123",
                "1",
                "0",
                3
        )
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `email and password are valid as user return true`() {
        val result = LoginUtil.validateLoginInput(
                "a@gmail.com",
                "aaaaaaa",
                "1",
                "0",
                1
        )
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `email and password are valid as admin return true`() {
        val result = LoginUtil.validateLoginInput(
                "c@gmail.com",
                "ccccccc",
                "0",
                "1",
                0
        )
        Truth.assertThat(result).isTrue()
    }

    @Test
    fun `email and password are wrong return false`() {
        val result = LoginUtil.validateLoginInput(
                "gedelixab@ub.ac.id",
                "password1235",
                "1",
                "0",
                2
        )
        Truth.assertThat(result).isFalse()
    }
}