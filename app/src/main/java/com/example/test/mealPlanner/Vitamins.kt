package com.example.test.mealPlanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.test.R

val Banana = Nutrition("Banana",5,5,5,5,5)
val Apple = Nutrition("Apple",7,7,7,7,7)
class Vitamins : Fragment(R.layout.fragment_vitamins){

    private var testingTextView:TextView? = null
    private val sharedViewModel:ShareFoodModel by activityViewModels()
    private val listOfIngredients = mutableListOf<Nutrition>(Banana, Apple)
    private val listOfIngredients2 = mutableListOf<Nutrition>()
    private var mCalories:TextView? = null
    private var mSodium:TextView?= null
    private var mCarbs:TextView? = null
    private var mFats:TextView? = null
    private var mProtein:TextView? = null
    private val testing = Nutrition("Testing",0,0,0,0,0)

    //============== Add Data To The Database ================
    //private val Test = Nutrition("ChangeforListner",0,0,0,0,0)
    //private var cloudfirebase:FirebaseDatabase = Firebase.database
    //private var clouddatabase:DatabaseReference = cloudfirebase.getReference("/Foods/Test")
    //========================================================
    
   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)

        testingTextView = requireView().findViewById(R.id.testingfun)
        mCalories = requireView().findViewById(R.id.CaloriesNum)
        mSodium = requireView().findViewById(R.id.SodiumNum)
        mCarbs = requireView().findViewById(R.id.CarbsNum)
        mFats = requireView().findViewById(R.id.FatsNum)
        mProtein = requireView().findViewById(R.id.ProteinNum)
        mCalories!!.text = testing.getCalories().toString()
        mSodium!!.text = testing.getSodium().toString()
        mCarbs!!.text = testing.getCarbohydrate().toString()
        mFats!!.text = testing.getFats().toString()
        mProtein!!.text = testing.getProtein().toString()

       sharedViewModel.userFoodItem1.observe(viewLifecycleOwner) { userFoodItem1 ->
           if (userFoodItem1 != null)
           {
               for (i in 0..1) {
                   if (userFoodItem1.getFoodName() == (listOfIngredients[i].getName())) {
                       listOfIngredients2.add(listOfIngredients[i])
                       addAllIngredients()
                       resetText()
                   }
               }
            }
       }
        sharedViewModel.userFoodItem2.observe(viewLifecycleOwner){ userFoodItem2 ->
            if(userFoodItem2!= null)
            {
                for(i in 0..1)
                {
                    if(userFoodItem2.getFoodName() == (listOfIngredients[i].getName()))
                    {
                        listOfIngredients2.add(listOfIngredients[i])
                    }
                }
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

            for (i in 0..listOfIngredients2.size)
            {
                testingCalories += listOfIngredients2[i].getCalories()
                testingSodium += listOfIngredients2[i].getSodium()
                testingCarbs += listOfIngredients2[i].getCarbohydrate()
                testingFats += listOfIngredients2[i].getFats()
                testingProtein += listOfIngredients2[i].getProtein()
            }




        }
        else if(listOfIngredients2.size == 1)
        {
            testing.setCalories(listOfIngredients2[0].getCalories())
            testing.setSodium(listOfIngredients2[0].getSodium())
            testing.setCarbohydrate(listOfIngredients2[0].getCarbohydrate())
            testing.setFats(listOfIngredients2[0].getFats())
            testing.setProtein(listOfIngredients2[0].getProtein())

        }


    }

}






/* Used to add information to the firebase */
// testingbutton1!!.setOnClickListener (object : View.OnClickListener{
// override fun onClick(view: View?){
// clouddatabase.push().setValue(Test)
//
// }
// })
// testingbutton1!!.setOnClickListener{
// clouddatabase.push().setValue(Test)
// testingTextView?.text = extractData?.getString("FirstTextComplete")
// }