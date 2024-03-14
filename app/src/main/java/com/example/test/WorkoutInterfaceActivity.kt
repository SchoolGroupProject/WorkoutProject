package com.example.test

import android.os.Bundle
import android.util.Log
import com.example.test.databinding.WorkoutInterfaceBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ServerValue

class WorkoutInterfaceActivity : MainActivity() {

    private lateinit var database: DatabaseReference //Initializes a database reference for Firebase
    private lateinit var _binding : WorkoutInterfaceBinding //Binds the workout button on main interface with the workout interface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = WorkoutInterfaceBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        initializeDbRef()
        initializeViews()
        allocateActivityTitle("Exercise")
    }

    private fun initializeDbRef() {
        database = FirebaseDatabase.getInstance().reference
    }

    private fun initializeViews() {
        wiWorkoutTypeInputEditText = findViewById(R.id.wiWorkoutTypeInput)

    }

    private lateinit var wiWorkoutTypeInputEditText: TextInputEditText

    data class WorkoutData(val userId: String, val workoutName: String, val timestamp: Any)

    private fun writeWorkoutToFirebase(userId: String) {
        val text = wiWorkoutTypeInputEditText.text.toString()
        val workoutData = WorkoutData(userId, text, ServerValue.TIMESTAMP)

        val workoutRef = database.child("workouts").push()
        workoutRef.setValue(workoutData)
            .addOnSuccessListener {
                Log.d("WorkoutInterface", "Workout data written successfully")
            }
            .addOnFailureListener { e ->
                Log.e("WorkoutInterface", "Error Writing workout data", e)
            }
    }

    private fun getUserId(): String {
        return "BillyG" //example user Id ** replace with logic to get the user Id on login
    }

    private fun writeToFirebase() {
        val userId = getUserId()
        writeWorkoutToFirebase(userId)
    }

    /*@IgnoreExtraProperties
    data class User(val username: String? = null, val email: String? = null) {
        fun writeNewUser(userId: String, name: String, email: String) {
            val activity = WorkoutInterfaceActivity()
            activity.initializeDbRef()

            val user = User(name, email)
            activity.database.child("users").child(userId).setValue(user)
        }
    }*/

    //Need to link the users login information with the creation of the new user so that they are logged into firebase and not hardcoded
}