package com.example.test

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
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
    private lateinit var _binding: WorkoutInterfaceBinding //Binds the workout button on main interface with the workout interface
    private lateinit var wiWorkoutTypeName: EditText
    private lateinit var wiAmountOfWeight: EditText
    private lateinit var wiAmountOfSets: EditText
    private lateinit var wiAmountOfReps: EditText
    private lateinit var wiSendToCalendar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = WorkoutInterfaceBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        initializeDbRef()
        initializeViews()
        allocateActivityTitle("Exercise")
        wiSendToCalendar = findViewById(R.id.wiSendToCalendar)

        wiWorkoutTypeName = findViewById(R.id.wiWorkoutTypeInput)
        wiAmountOfWeight = findViewById(R.id.wiWeightInput)
        wiAmountOfSets = findViewById(R.id.wiSetsInput)
        wiAmountOfReps = findViewById(R.id.wiRepsInput)

        wiSendToCalendar.setOnClickListener {
            saveWorkoutData()
        }
    }

    private fun initializeDbRef() {
        database = FirebaseDatabase.getInstance().getReference("Workouts")
    }

    private fun initializeViews() {
        wiWorkoutTypeInputEditText = findViewById(R.id.wiWorkoutTypeInput)

    }

    private lateinit var wiWorkoutTypeInputEditText: TextInputEditText

    /*data class WorkoutData(val userId: String, val workoutName: String, val timestamp: Any)*/

    /*private fun writeWorkoutToFirebase(userId: String) {
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
    }*/

    /*private fun getUserId(): String {
        return "BillyG" //example user Id ** replace with logic to get the user Id on login
    }*/

    /*private fun writeToFirebase() {
        val userId = getUserId()
        writeWorkoutToFirebase(userId)
    }*/

    //Saves the workout type
    private fun saveWorkoutData() {
        val workoutTypeName = wiWorkoutTypeName.text.toString().trim()
        val amountOfWeight = wiAmountOfWeight.text.toString().trim()
        val amountOfSets = wiAmountOfSets.text.toString().trim()
        val amountOfReps = wiAmountOfReps.text.toString().trim()

        if (workoutTypeName.isEmpty()) {
            wiWorkoutTypeName.error = "Please enter workout name"
        }
        if (amountOfWeight.isEmpty()) {
            wiAmountOfWeight.error = "Please enter the weight"
        }
        if (amountOfSets.isEmpty()) {
            wiAmountOfReps.error = "Please enter amount of sets"
        }
        if (amountOfReps.isEmpty()) {
            wiAmountOfSets.error = "Please enter amount of reps"
        }

        val workoutId = database.push().key!!
        if (workoutId.isEmpty()) {
            Toast.makeText(this, "Failed to generate a unique ID", Toast.LENGTH_SHORT).show()
            return
        }

        val workout = WorkoutModel(workoutId, workoutTypeName, amountOfWeight, amountOfSets, amountOfReps)

        database.child(workoutId).setValue(workout)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{ err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
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
}

