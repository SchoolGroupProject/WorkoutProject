package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test.databinding.ActivityHomepageBinding

class Homepage : MainActivity() {


        private lateinit var _binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityHomepageBinding.inflate(layoutInflater)

        setContentView(_binding.root)
    }
}