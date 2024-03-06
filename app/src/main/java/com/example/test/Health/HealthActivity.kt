package com.example.test.Health

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.databinding.ActivityHealthBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HealthActivity : MainActivity() {

    private lateinit var healthLayout:TabLayout
    private lateinit var healthyPager: ViewPager2
    private lateinit var health_binding:ActivityHealthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        health_binding = ActivityHealthBinding.inflate(layoutInflater)
        setContentView(health_binding.root)
        allocateActivityTitle("Health")

        healthLayout = findViewById(R.id.healthsLayout)
        healthyPager = findViewById(R.id.healthpager)
        healthyPager.adapter = HealthAdapter(this)
        TabLayoutMediator(healthLayout,healthyPager){
            tab,index -> tab.text = when(index) {
                0 -> {"Physical"}
                1 -> {"Mental"}
            else -> {throw NotFoundException("Position Not Found")}
            }
        }.attach()



    }
}