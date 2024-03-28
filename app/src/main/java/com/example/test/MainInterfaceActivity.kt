package com.example.test

import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.MainInterfaceBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainInterfaceActivity : MainActivity() {

    private lateinit var _binding: MainInterfaceBinding
    private lateinit var database: DatabaseReference
    private lateinit var mainWorkoutTypeText: TextView
    private lateinit var mainWeightText: TextView
    private lateinit var mainWorkoutReps: TextView
    private lateinit var mainWorkoutSets: TextView
    private lateinit var mainDate: TextView

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         _binding = MainInterfaceBinding.inflate(layoutInflater)
         setContentView(_binding.root)
         allocateActivityTitle("Home")
         mainWorkoutTypeText = findViewById(R.id.mainWorkoutTypeText)
         mainWeightText = findViewById(R.id.mainWeightText)
         mainWorkoutReps = findViewById(R.id.mainWorkoutReps)
         mainWorkoutSets = findViewById(R.id.mainWorkoutSets)
         mainDate = findViewById(R.id.mainDate)
         initializeDbRef()

         val workoutButton: ImageButton = findViewById(R.id.mainWorkoutButton)

         val workoutDate : String = "March 28th, 2024"

         workoutButton.setOnClickListener {
             val buttonIntent = Intent(this, WorkoutInterfaceActivity::class.java)
             startActivity(intent)
         }

         val sharedPreferences = this.getSharedPreferences("WorkoutPrefs", Context.MODE_PRIVATE)
         val workoutId = sharedPreferences.getString("WORKOUT_ID", "")

         readData(workoutId.toString())
     }

    private fun initializeDbRef() {
        database = FirebaseDatabase.getInstance().getReference("Workouts")
    }

    /*private fun readWorkoutData(workoutId: String) {
        val workoutRef = database.child(workoutId)

        workoutRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val workoutTypeName = snapshot.child("workoutTypeName").getValue(String::class.java)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }*/

    private fun readData(workoutId: String) {
        database.child(workoutId).get().addOnSuccessListener {
            if (it.exists()) {
                val workoutType = it.child("workoutTypeName").value
                val weight = it.child("amountOfWeight").value
                val sets = it.child("amountOfSets").value
                val reps = it.child("amountOfReps").value
                val workoutDate = "March 28th, 2024"
                updateWorkout(workoutDate, workoutType.toString(), weight.toString(), sets.toString(), reps.toString())
            }
            else {
                Toast.makeText(this, "Date has no workout associated with it", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun updateWorkout(workoutDate: String, workoutTypeName: String, amountOfWeight: String, amountOfSets: String, amountOfReps: String) {
        _binding.mainDate.text = workoutDate
        _binding.mainWorkoutTypeText.text = workoutTypeName
        mainWeightText.text = amountOfWeight
        mainWorkoutSets.text = amountOfSets
        mainWorkoutReps.text = amountOfReps
    }
}