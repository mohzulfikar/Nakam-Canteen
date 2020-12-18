package com.example.nk2

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegisterUtilTest {
    @Test
    fun `empty email or password return false`() {
        val result = RegisterUtil.validateRegistrationInput(
                "",
                "12345678",
                "Fikri",
                "185150200111017",
                "08229444444"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password less than 6 return false`() {
        val result = RegisterUtil.validateRegistrationInput(
                "gedelixa@gmail.com",
                "abc",
                "12345678",
                "185150200111017",
                "08229444444"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `email or password or telp more than 32 return false`() {
        val result = RegisterUtil.validateRegistrationInput(
                "iniadalahsesuatuyangpanjangsekalipanjangpanjangpanjang@ub.ac.id",
                "iniadalahsesuatuyangpanjangsekalipanjangpanjangpanjang123456",
                "12345678",
                "185150200111017",
                "08229444444"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `email exist return false`() {
        val result = RegisterUtil.validateRegistrationInput(
                "gedelixa@ub.ac.id",
                "abcdefgh",
                "Rafi",
                "185150200111018",
                "08229444444"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `telp exist return false`() {
        val result = RegisterUtil.validateRegistrationInput(
                "rafi@ub.ac.id",
                "abcdefgh123",
                "Rafi",
                "185150200111018",
                "082299044363"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `nim exist return false`() {
        val result = RegisterUtil.validateRegistrationInput(
                "rafi@ub.ac.id",
                "abcdefgh123",
                "Rafi",
                "185150200111017",
                "082299041960"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password non alphanumeric return false`() {
        val result = RegisterUtil.validateRegistrationInput(
                "rafffi@ub.ac.id",
                "bacab",
                "Rafi",
                "1851520011121",
                "0822911212"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `not using last email as ub ac id return false`() {
        val result = RegisterUtil.validateRegistrationInput(
                "rafi@gmail.com",
                "abcdefghijklmn",
                "Rafi",
                "185150200111018",
                "082299041960"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `telp non numeric return false`() {
        val result = RegisterUtil.validateRegistrationInput(
                "rafi@ub.ac.id",
                "abcdefghijklmn123",
                "Rafi",
                "185150200111018",
                "ininomortelepon"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid email and password more than 6 return true`() {
        val result = RegisterUtil.validateRegistrationInput(
                "gedelixa2@ub.ac.id",
                "adfia290fajf9",
                "Fikri",
                "185150200111011",
                "082294444424"
        )
        assertThat(result).isTrue()
    }
}