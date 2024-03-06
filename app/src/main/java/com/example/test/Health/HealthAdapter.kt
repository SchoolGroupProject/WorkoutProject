package com.example.test.Health

import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HealthAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
            return when(position)
            {
                0 -> {PhysicalHealth()}
                1 -> {MentalHealth()}
                else -> {throw NotFoundException("Position Not Found")}
            }

    }


}