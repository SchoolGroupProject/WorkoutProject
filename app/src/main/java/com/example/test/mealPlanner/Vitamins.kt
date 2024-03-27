package com.example.test.mealPlanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.test.R


class Vitamins : Fragment(R.layout.fragment_vitamins){

    private val sharedViewModel:ShareFoodModel by activityViewModels()
    private val listOfIngredients2 = mutableListOf<DataCompiler>()
    private var mCalories:TextView? = null
    private var mSodium:TextView?= null
    private var mCarbs:TextView? = null
    private var mFats:TextView? = null
    private var mProtein:TextView? = null
    private val testing = Nutrition("testing",0,0,0,0,0)
    private val nullNutrition = Nutrition("Null",0,0,0,0,0)

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
        //Text displays
        mCalories = requireView().findViewById(R.id.CaloriesNum)
        mSodium = requireView().findViewById(R.id.SodiumNum)
        mCarbs = requireView().findViewById(R.id.CarbsNum)
        mFats = requireView().findViewById(R.id.FatsNum)
        mProtein = requireView().findViewById(R.id.ProteinNum)
       //For initial start
        mCalories!!.text = testing.getCalories().toString()
        mSodium!!.text = testing.getSodium().toString()
        mCarbs!!.text = testing.getCarbohydrate().toString()
        mFats!!.text = testing.getFats().toString()
        mProtein!!.text = testing.getProtein().toString()
        //When data changes between fragments cycle
       sharedViewModel.userFoodItem1.observe(viewLifecycleOwner){ userFoodItem1 ->
           if (userFoodItem1 != null)
           {
               if(userFoodItem1.getFood() != nullNutrition) {
                   removeIngredients(0)
                   listOfIngredients2.add(0, userFoodItem1)
                   addAllIngredients()
                   resetText()
               }
               else{
                   removeIngredients(0)
                   addAllIngredients()
                   resetText()
               }
           }
       }
       sharedViewModel.userFoodItem2.observe(viewLifecycleOwner){ userFoodItem2 ->
            if(userFoodItem2 != null)
            {
                if(userFoodItem2.getFood() != nullNutrition) {
                    removeIngredients(1)
                    listOfIngredients2.add(1, userFoodItem2)
                    addAllIngredients()
                    resetText()
                }
                else{
                removeIngredients(1)
                addAllIngredients()
                resetText()
                }
            }
        }
       sharedViewModel.userFoodItem3.observe(viewLifecycleOwner){ userFoodItem3 ->
           if(userFoodItem3 != null)
           {
               if(userFoodItem3.getFood() != nullNutrition) {
                   removeIngredients(2)
                   listOfIngredients2.add(2, userFoodItem3)
                   addAllIngredients()
                   resetText()
               }
               else{
                   removeIngredients(2)
                   addAllIngredients()
                   resetText()
               }
           }
       }
       sharedViewModel.userFoodItem4.observe(viewLifecycleOwner){ userFoodItem4 ->
           if(userFoodItem4 != null)
           {
               if(userFoodItem4.getFood() != nullNutrition) {
                   removeIngredients(3)
                   listOfIngredients2.add(3, userFoodItem4)
                   addAllIngredients()
                   resetText()
               }
               else{
                   removeIngredients(3)
                   addAllIngredients()
                   resetText()
               }
           }
       }
       sharedViewModel.userFoodItem5.observe(viewLifecycleOwner){ userFoodItem5 ->
           if(userFoodItem5 != null)
           {
               if(userFoodItem5.getFood() != nullNutrition) {
                   removeIngredients(4)
                   listOfIngredients2.add(4, userFoodItem5)
                   addAllIngredients()
                   resetText()
               }
               else{
                   removeIngredients(4)
                   addAllIngredients()
                   resetText()
               }
           }
       }
       sharedViewModel.userFoodItem6.observe(viewLifecycleOwner){ userFoodItem6 ->
           if(userFoodItem6 != null)
           {
               if(userFoodItem6.getFood() != nullNutrition) {
                   removeIngredients(5)
                   listOfIngredients2.add(5, userFoodItem6)
                   addAllIngredients()
                   resetText()
               }
               else{
                   removeIngredients(5)
                   addAllIngredients()
                   resetText()
               }
           }
       }
       sharedViewModel.userFoodItem7.observe(viewLifecycleOwner){ userFoodItem7 ->
           if(userFoodItem7 != null)
           {
               if(userFoodItem7.getFood() != nullNutrition) {
                   removeIngredients(6)
                   listOfIngredients2.add(6, userFoodItem7)
                   addAllIngredients()
                   resetText()
               }
               else{
                   removeIngredients(6)
                   addAllIngredients()
                   resetText()
               }
           }
       }

    }

    //Functions to fix several problems
    private fun removeIngredients(index:Int) {
           if(listOfIngredients2.isNotEmpty()) {
               try {
                    listOfIngredients2.removeAt(index)
               } catch (e: RuntimeException) {
                   println("NotFound")
               }
           }
    }

    private fun resetText() {
        mCalories!!.text = testing.getCalories().toString()
        mSodium!!.text = testing.getSodium().toString()
        mCarbs!!.text = testing.getCarbohydrate().toString()
        mFats!!.text = testing.getFats().toString()
        mProtein!!.text = testing.getProtein().toString()
    }

    private fun addAllIngredients() {

        if(listOfIngredients2.isNotEmpty() && listOfIngredients2.size != 1)
        {
            var testingCalories = 0
            var testingSodium = 0
            var testingCarbs = 0
            var testingFats = 0
            var testingProtein = 0

            for (i in 0 until listOfIngredients2.size)
            {
                testingCalories += listOfIngredients2[i].getFood().getCalories() * listOfIngredients2[i].getFoodQuantity()
                testingSodium += listOfIngredients2[i].getFood().getSodium() * listOfIngredients2[i].getFoodQuantity()
                testingCarbs += listOfIngredients2[i].getFood().getCarbohydrate() * listOfIngredients2[i].getFoodQuantity()
                testingFats += listOfIngredients2[i].getFood().getFats() * listOfIngredients2[i].getFoodQuantity()
                testingProtein += listOfIngredients2[i].getFood().getProtein() * listOfIngredients2[i].getFoodQuantity()
            }
            testing.setCalories(testingCalories)
            testing.setSodium(testingSodium)
            testing.setCarbohydrate(testingCarbs)
            testing.setFats(testingFats)
            testing.setProtein(testingProtein)
        }
        else if(listOfIngredients2.size == 1)
        {
            testing.setCalories(
                listOfIngredients2[0].getFood().getCalories() * listOfIngredients2[0].getFoodQuantity()
            )
            testing.setSodium(
                listOfIngredients2[0].getFood().getSodium() * listOfIngredients2[0].getFoodQuantity()
            )
            testing.setCarbohydrate(
                listOfIngredients2[0].getFood().getCarbohydrate() * listOfIngredients2[0].getFoodQuantity()
            )
            testing.setFats(
                listOfIngredients2[0].getFood().getFats() * listOfIngredients2[0].getFoodQuantity()
            )
            testing.setProtein(
                listOfIngredients2[0].getFood().getProtein() * listOfIngredients2[0].getFoodQuantity()
            )
        }


    }

}
