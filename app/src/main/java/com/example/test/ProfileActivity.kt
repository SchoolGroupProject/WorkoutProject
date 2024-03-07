package com.example.test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.test.databinding.ActivityProfileBinding
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : ComponentActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        val saveButton = findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            val profileName = binding.editTextProfileName.text.toString().trim()
            val height = binding.editTextHeight.text.toString().trim()
            val weight = binding.editTextWeight.text.toString().trim()
            val sex = binding.editTextSex.text.toString().trim()
            val goalWeight = binding.editTextGoalWeight.text.toString().trim()

            saveUserData(profileName, height, weight, sex, goalWeight)
        }

    }

    private fun saveUserData(profileName: String, height: String, weight: String, sex: String, goalWeight: String) {
        val db = FirebaseFirestore.getInstance()
        val userMap = hashMapOf(
            "profileName" to profileName,
            "height" to height,
            "weight" to weight,
            "sex" to sex,
            "goalWeight" to goalWeight
        )
        db.collection("users").document("userId")
            .set(userMap)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
            }
    }

}