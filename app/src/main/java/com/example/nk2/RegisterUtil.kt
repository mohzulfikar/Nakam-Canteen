package com.example.nk2

object RegisterUtil {
    private val existingEmail = listOf("gedelixa@ub.ac.id", "john@doe.com", "a@gmail.com", "b@gmail.com")
    private val existingTelp = listOf("082299044363", "0877080808", "08634365", "08080808")
    private val existingNim = listOf("185150200111017", "18111110000", "18515151515000", "181551058195")

    // Method validate register with the logics
    fun validateRegistrationInput(
            email: String,
            password: String,
            nama: String,
            nim: String,
            telp: String
    ): Boolean {
        // Mengecek email atau password belum diisi
        if (email.isEmpty() || password.isEmpty()) {
            return false
        }
        // Mengecek password yang < 6 character
        if (password.length < 6) {
            return false
        }
        // Mengecek email atau password atau no. telepon yg panjangnya > 32
        if (email.length > 32 || password.length > 32 || telp.length > 32) {
            return false
        }
        // Mengecek apakah email sudah ada
        if (email in existingEmail) {
            return false
        }
        // Mengecek apakah no. telp sudah ada
        if (telp in existingTelp) {
            return false
        }
        // Mengecek apakah nim sudah ada
        if (nim in existingNim) {
            return false
        }
        // Mengecek password yg hanya boleh alphanumeric
        if (!password.matches("^[a-zA-Z0-9]*$".toRegex()) && password.matches("-?\\d+(\\.\\d+)?".toRegex())
                && password.matches("^[a-zA-Z]*$".toRegex())) {
            return false
        }
        // Mengecek apakah email menggunakan akhiran ub
        if (email.takeLast(8) != "ub.ac.id") {
            return false
        }
        // Mengecek apakah nomor telepon yang diberikan adalah integer
        if (!telp.matches("-?\\d+(\\.\\d+)?".toRegex())) {
            return false
        }
        return true
    }
}