package com.example.test

import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent
import com.example.test.databinding.MainInterfaceBinding

class MainInterfaceActivity : MainActivity() {

    private lateinit var _binding: MainInterfaceBinding
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
     }
}