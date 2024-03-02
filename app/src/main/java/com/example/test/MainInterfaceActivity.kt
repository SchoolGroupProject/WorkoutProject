package com.example.test

import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent

class MainInterfaceActivity : MainActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.main_interface)

         val workoutButton: ImageButton = findViewById(R.id.mainWorkoutButton)

         workoutButton.setOnClickListener {
             val intent = Intent(this, WorkoutInterfaceActivity::class.java)
             startActivity(intent)
         }
     }
}