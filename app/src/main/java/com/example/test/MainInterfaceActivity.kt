package com.example.test

import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent
import com.example.test.databinding.MainInterfaceBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
class MainInterfaceActivity : MainActivity() {

    private lateinit var _binding: MainInterfaceBinding
    private lateinit var database: DatabaseReference

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         _binding = MainInterfaceBinding.inflate(layoutInflater)
         setContentView(_binding.root)
         allocateActivityTitle("Home")

         val workoutButton: ImageButton = findViewById(R.id.mainWorkoutButton)

         workoutButton.setOnClickListener {
             val intent = Intent(this, WorkoutInterfaceActivity::class.java)
             startActivity(intent)
         }

         database = FirebaseDatabase.getInstance().reference.child("users")
     }

    data class User(val username: String? = null, val email: String? = null)
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
    }
}