package com.example.test.mealPlanner

import android.content.res.Resources.NotFoundException
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.databinding.ActivityFoodBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.FirebaseDatabase
import java.time.DayOfWeek
import java.util.Calendar

class FoodActivity : MainActivity(){

    private lateinit var foodLayout : TabLayout
    private lateinit var foodPager : ViewPager2
    private lateinit var foodnavi: ActivityFoodBinding
    private lateinit var foodCalendar:ImageButton
    private lateinit var foodDayOfWeek: TextView
    private var mCurrentdate:Calendar = Calendar.getInstance()
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

        foodCalendar = findViewById(R.id.calendarfoodbutton)
        foodDayOfWeek = findViewById(R.id.dayofweek)


        var day = mCurrentdate.get(Calendar.DAY_OF_WEEK)

        resetDayTextView(day)

        foodCalendar.setOnClickListener(object : OnClickListener{
            override fun onClick(v: View?) {









            }

        })









        //sharedViewModel.fullReset()

    }

    private fun resetDayTextView(num:Int) {
        when(num)
        {
            2->{foodDayOfWeek.text = "Monday"}
            3->{foodDayOfWeek.text = "Tuesday"}
            4->{foodDayOfWeek.text = "Wednesday"}
            5->{foodDayOfWeek.text = "Thursday"}
            6->{foodDayOfWeek.text = "Friday"}
            7->{foodDayOfWeek.text = "Saturday"}
            1->{foodDayOfWeek.text = "Sunday"}
        }
    }

}
