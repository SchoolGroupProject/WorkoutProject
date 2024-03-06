package com.example.test

import android.os.Bundle
import com.example.test.databinding.WorkoutInterfaceBinding


class WorkoutInterfaceActivity : MainActivity() {
    private lateinit var _binding : WorkoutInterfaceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = WorkoutInterfaceBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        allocateActivityTitle("Exercise")
    }
}