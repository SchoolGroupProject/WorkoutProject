package com.example.test

import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent
import android.widget.TextView
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

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         _binding = MainInterfaceBinding.inflate(layoutInflater)
         setContentView(_binding.root)
         allocateActivityTitle("Home")
         mainWorkoutTypeText = findViewById(R.id.mainWorkoutTypeText)
         mainWeightText = findViewById(R.id.mainWeightText)
         mainWorkoutReps = findViewById(R.id.mainWorkoutReps)
         mainWorkoutSets = findViewById(R.id.mainWorkoutSets)
         initializeDbRef()

         val workoutButton: ImageButton = findViewById(R.id.mainWorkoutButton)

         workoutButton.setOnClickListener {
             val intent = Intent(this, WorkoutInterfaceActivity::class.java)
             startActivity(intent)
         }

         /*val userId = "tempUserId"
         readUserData(userId) { user ->
             if (user != null) {
                 updateWorkoutType(user.username ?: "")
             }
             else {
                 //solve any data related issues
             }
         }*/
     }

    private fun initializeDbRef() {
        database = FirebaseDatabase.getInstance().getReference("Workouts")
    }

    /*data class User(val username: String? = null, val email: String? = null)
    fun readUserData(userId: String, callback: (User?) -> Unit) {
        val userRef = database.child(userId)

        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                callback(user)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(null)
            }
        })
    }*/

    private fun updateWorkoutType(workoutType: String) {

    }
}