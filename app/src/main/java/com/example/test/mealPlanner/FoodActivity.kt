package com.example.test.mealPlanner

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.databinding.ActivityFoodBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FoodActivity : MainActivity(){

    private lateinit var foodLayout : TabLayout
    private lateinit var foodPager : ViewPager2
    private lateinit var foodnavi: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodnavi = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(foodnavi.root)
        allocateActivityTitle("Meal Planner")

        foodLayout = findViewById(R.id.foodtab)
        foodPager = findViewById(R.id.foodviewpager)
        foodPager.adapter = FoodAdapter(this)
        TabLayoutMediator(foodLayout,foodPager){
            tab,index -> tab.text = when(index){
                0 -> {"Planner"}
                1 -> {"Health"}
                else -> {throw NotFoundException("Position Not Found")}
            }
        }.attach()


    }

}
