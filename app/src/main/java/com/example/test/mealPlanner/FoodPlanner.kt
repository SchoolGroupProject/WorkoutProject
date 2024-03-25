package com.example.test.mealPlanner

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.test.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.Firebase




class FoodPlanner : Fragment(R.layout.fragment_food_planner) {

    //Data for testing
    private val Banana = Nutrition("Banana",5,5,5,5,5)
    private val Apple = Nutrition("Apple",7,7,7,7,7)
    private val nullNutrition = Nutrition("Null",0,0,0,0,0)
    private val foodItems = mutableListOf(Banana.getName(),Apple.getName())
    private val ingredientsList = mutableListOf(Banana,Apple)
    //For Data sharing between fragments
    private val sharedViewModel:ShareFoodModel by activityViewModels()
    //Below is all of the frontend syncs for editing
    private var foodAuto1:AutoCompleteTextView? = null
    private var foodAuto2:AutoCompleteTextView? = null
    private var foodAuto3:AutoCompleteTextView? = null
    private var foodAuto4:AutoCompleteTextView? = null
    private var foodAuto5:AutoCompleteTextView? = null
    private var foodAuto6:AutoCompleteTextView? = null
    private var foodAuto7:AutoCompleteTextView? = null
    private var itemQuan1:EditText? = null
    private var itemQuan2:EditText? = null
    private var itemQuan3:EditText? = null
    private var itemQuan4:EditText? = null
    private var itemQuan5:EditText? = null
    private var itemQuan6:EditText? = null
    private var itemQuan7:EditText? = null
    private var dataTransferButton:Button? = null

    //Function for shifting through data per text
    private fun getInformation(name:String):Nutrition {

        var testing:Nutrition = nullNutrition

        for (i in 0 until ingredientsList.size)
        {
            if(ingredientsList[i].getName() == name)
            {
                testing = ingredientsList[i]
            }
        }

        return testing

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            //Reset the share model on every reopen
            //sharedViewModel.fullReset()
        /* List of variables to assign */
        val foodItemBinder = ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,foodItems)
        dataTransferButton = requireView().findViewById(R.id.applydata)

        val foodNumberBinding =
            mutableListOf(itemQuan1,itemQuan2,itemQuan3,itemQuan4,itemQuan5,itemQuan6,itemQuan7)

        val foodTextBinding =
            mutableListOf(foodAuto1,foodAuto2,foodAuto3,foodAuto4,foodAuto5,foodAuto6,foodAuto7)

        val listOfIds =
            mutableListOf(R.id.tvFood1,R.id.tvFood2,R.id.tvFood3,R.id.tvFood4,R.id.tvFood5,R.id.tvFood6,R.id.tvFood7)

        val listOfNumIds =
            mutableListOf(R.id.Quantity1,R.id.Quantity2,R.id.Quantity3,R.id.Quantity4,R.id.Quantity5,R.id.Quantity6,R.id.Quantity7)


        //For the AutoCompletes
        for (i in 0..6)
        {
            foodTextBinding[i] = requireView().findViewById(listOfIds[i])
            foodTextBinding[i]?.setAdapter(foodItemBinder)
        }
        //For the quantities
        for(i in 0..6)
        {
            foodNumberBinding[i] = requireView().findViewById(listOfNumIds[i])
        }


        var storage = ""

        foodTextBinding[0]!!.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                storage = s.toString()
            }
        })

        var numStorage = 0

        foodNumberBinding[0]!!.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    numStorage = s.toString().toInt()
                    val dataTransfer = DataCompiler(getInformation(storage), numStorage)
                    sharedViewModel.saveUserFoodItem(0, dataTransfer)
                }
            }

        })





        //For the on change effect for the vitamin fragment
//        dataTransferButton?.setOnClickListener(object : View.OnClickListener {
//           override fun onClick(view: View?) {
//
//                for(i in 0..6)
//                {
//                    if(foodTextBinding[i]!!.text.isNotEmpty())
//                    {
//                        val storage = DataCompiler(getInformation(foodTextBinding[i]!!.text.toString()),foodNumberBinding[i]!!.text.toString().toInt())
//                        sharedViewModel.saveUserFoodItem(i,storage)
//                    }
//                    else
//                    {
//                        val storage = DataCompiler(nullNutrition,0)
//                        sharedViewModel.saveUserFoodItem(i,storage)
//                    }
//                }
//
//           }
//
//        })

    }

}
