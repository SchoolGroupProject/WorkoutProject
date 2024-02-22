package com.example.test.mealPlanner

import android.content.res.Resources.NotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.databinding.ActivityFoodBinding
import com.example.test.databinding.ActivityMainBinding
import com.example.test.databinding.AppBarMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FoodActivity : AppCompatActivity(){

    private lateinit var foodLayout : TabLayout
    private lateinit var foodPager : ViewPager2
    private lateinit var foodnavi: ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        foodnavi = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(foodnavi.root)


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
