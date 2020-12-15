package com.example.nk2

import com.google.firebase.firestore.FirebaseFirestore

object RegisterUtil {
    // Grab from db
    val fStore = FirebaseFirestore.getInstance()
    val df = fStore.collection("User").get()




    private val existingEmail = listOf("rafi@gmail.com", "fikar@gmail.com", "afif@gmail.com",
        "misba@gmail.com", "bana@gmail.com")
    private val existingTelp = listOf("082299041960", "082288496839", "081386935867")

    // Method validate register with the logics
    fun validateRegistrationInput(
        email: String,
        password: String,
        nama: String,
        nim: String,
        telp: String
    ): Boolean {
        if (email.isEmpty() || password.isEmpty()){
            return false
        }
        if(password.length < 6){
            return false
        }
        if(email.length > 32 || password.length > 32 || telp.length > 32){
            return false
        }
        if(email in existingEmail){
            return false
        }
        if(telp in existingTelp){
            return false
        }
        if(!password.matches("^[a-zA-Z0-9]*$".toRegex())){
            return false
        }
        if(email.takeLast(8) != "ub.ac.id"){
            return false
        }
        if(!telp.matches("-?\\d+(\\.\\d+)?".toRegex())){
            return false
        }
        return true
    }
}