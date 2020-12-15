package com.example.nk2

import com.google.firebase.auth.FirebaseAuth

object LoginUtil {
    val fAuth = FirebaseAuth.getInstance()
    // Grab from db
    private val dbEmail = "rafi@ub.ac.id"
    private val dbPassword = "abcdefg4nda"

    // Method validate login with the logics
    fun validateLoginInput(
        email: String,
        password: String,
        maxAttempt: Int
    ): Boolean {
        if (maxAttempt > 2){
            return false
        }
        if(!fAuth.signInWithEmailAndPassword(email,password).isSuccessful){
            return false
        }
        return true
    }
}