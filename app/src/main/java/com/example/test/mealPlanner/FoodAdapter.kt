package com.example.test.mealPlanner

import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FoodAdapter(activity: FragmentActivity):FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {FoodPlanner()}
            1 -> {Vitamins()}
            else-> {throw NotFoundException("Position Not Found")}
        }
    }

}