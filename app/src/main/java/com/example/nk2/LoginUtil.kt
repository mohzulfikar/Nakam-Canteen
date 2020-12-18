package com.example.nk2

object LoginUtil {
    private val dbEmail = arrayListOf<String>("gedelixa@ub.ac.id", "a@gmail.com", "b@gmail.com", "c@gmail.com")
    private val dbPassword = arrayListOf<String>("password123", "aaaaaaa", "bbbbbbb", "ccccccc")
    private val isUser = arrayListOf<String>("1", "1", "1", "0")
    private val isAdmin = arrayListOf<String>("0", "0", "0", "1")

    // Method validate login with the logics
    fun validateLoginInput(
            email: String,
            password: String,
            isUser: String,
            isAdmin: String,
            maxAttempt: Int
    ): Boolean {
        var hasil = true

        // Percocbaan login > 2
        if (maxAttempt > 2) {
            return false
        }
        // Gagal login
        for (i in 0..3) {
            if (dbEmail.get(i) == email && dbPassword.get(i) == password && this.isUser.get(i) == isUser
                    && this.isAdmin.get(i) == isAdmin) {
                hasil = true
                break
            } else {
                hasil = false
            }
        }
        return hasil
    }
}