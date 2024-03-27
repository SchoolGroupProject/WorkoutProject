package com.example.test.mealPlanner

import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
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
import com.google.firebase.firestore.firestore


class FoodPlanner : Fragment(R.layout.fragment_food_planner) {

    //For firebase
    private val databaseFirestore = Firebase.firestore

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
    private var foodSaveButton:Button? = null

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

        val foodNumberBinding =
            mutableListOf(itemQuan1,itemQuan2,itemQuan3,itemQuan4,itemQuan5,itemQuan6,itemQuan7)

        val foodTextBinding =
            mutableListOf(foodAuto1,foodAuto2,foodAuto3,foodAuto4,foodAuto5,foodAuto6,foodAuto7)

        val listOfIds =
            mutableListOf(R.id.tvFood1,R.id.tvFood2,R.id.tvFood3,R.id.tvFood4,R.id.tvFood5,R.id.tvFood6,R.id.tvFood7)

        val listOfNumIds =
            mutableListOf(R.id.Quantity1,R.id.Quantity2,R.id.Quantity3,R.id.Quantity4,R.id.Quantity5,R.id.Quantity6,R.id.Quantity7)


        foodSaveButton = requireView().findViewById(R.id.SaveMeal)


        //For the AutoCompletes starting
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

        //Adding watchers to the text edits
        var storage = ""
        var numStorage = 0
        foodTextBinding[0]!!.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    storage = s.toString()
                }
                else
                {
                    val neg:Editable = SpannableStringBuilder("")
                    foodNumberBinding[0]!!.text = neg
                }
            }
        })
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
                else
                {
                    val testdata = DataCompiler(nullNutrition,0)
                    sharedViewModel.saveUserFoodItem(0,testdata)
                }

            }

        })

        var storage1 = ""
        var numStorage1 = 0
        foodTextBinding[1]!!.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    storage1 = s.toString()
                }
                else
                {
                    val neg:Editable = SpannableStringBuilder("")
                    foodNumberBinding[1]!!.text = neg
                }
            }
        })
        foodNumberBinding[1]!!.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    numStorage1 = s.toString().toInt()
                    val dataTransfer = DataCompiler(getInformation(storage1), numStorage1)
                    sharedViewModel.saveUserFoodItem(1, dataTransfer)
                }
                else
                {
                    val testdata = DataCompiler(nullNutrition,0)
                    sharedViewModel.saveUserFoodItem(1,testdata)
                }

            }

        })

        var storage2 = ""
        var numStorage2 = 0
        foodTextBinding[2]!!.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    storage2 = s.toString()
                }
                else
                {
                    val neg:Editable = SpannableStringBuilder("")
                    foodNumberBinding[2]!!.text = neg
                }
            }
        })
        foodNumberBinding[2]!!.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    numStorage2 = s.toString().toInt()
                    val dataTransfer = DataCompiler(getInformation(storage2), numStorage2)
                    sharedViewModel.saveUserFoodItem(2, dataTransfer)
                }
                else
                {
                    val testdata = DataCompiler(nullNutrition,0)
                    sharedViewModel.saveUserFoodItem(2,testdata)
                }

            }

        })

        var storage3 = ""
        var numStorage3 = 0
        foodTextBinding[3]!!.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    storage3 = s.toString()
                }
                else
                {
                    val neg:Editable = SpannableStringBuilder("")
                    foodNumberBinding[3]!!.text = neg
                }
            }
        })
        foodNumberBinding[3]!!.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    numStorage3 = s.toString().toInt()
                    val dataTransfer = DataCompiler(getInformation(storage3), numStorage3)
                    sharedViewModel.saveUserFoodItem(3, dataTransfer)
                }
                else
                {
                    val testdata = DataCompiler(nullNutrition,0)
                    sharedViewModel.saveUserFoodItem(3,testdata)
                }

            }

        })

        var storage4 = ""
        var numStorage4 = 0
        foodTextBinding[4]!!.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    storage4 = s.toString()
                }
                else
                {
                    val neg:Editable = SpannableStringBuilder("")
                    foodNumberBinding[4]!!.text = neg
                }
            }
        })
        foodNumberBinding[4]!!.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    numStorage4 = s.toString().toInt()
                    val dataTransfer = DataCompiler(getInformation(storage4), numStorage4)
                    sharedViewModel.saveUserFoodItem(4, dataTransfer)
                }
                else
                {
                    val testdata = DataCompiler(nullNutrition,0)
                    sharedViewModel.saveUserFoodItem(4,testdata)
                }

            }

        })

        var storage5 = ""
        var numStorage5 = 0
        foodTextBinding[5]!!.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    storage5 = s.toString()
                }
                else
                {
                    val neg:Editable = SpannableStringBuilder("")
                    foodNumberBinding[5]!!.text = neg
                }
            }
        })
        foodNumberBinding[5]!!.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    numStorage5 = s.toString().toInt()
                    val dataTransfer = DataCompiler(getInformation(storage5), numStorage5)
                    sharedViewModel.saveUserFoodItem(5, dataTransfer)
                }
                else
                {
                    val testdata = DataCompiler(nullNutrition,0)
                    sharedViewModel.saveUserFoodItem(5,testdata)
                }

            }

        })

        var storage6 = ""
        var numStorage6 = 0
        foodTextBinding[6]!!.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    storage6 = s.toString()
                }
                else
                {
                    val neg:Editable = SpannableStringBuilder("")
                    foodNumberBinding[6]!!.text = neg
                }
            }
        })
        foodNumberBinding[6]!!.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty()) {
                    numStorage6 = s.toString().toInt()
                    val dataTransfer = DataCompiler(getInformation(storage6), numStorage6)
                    sharedViewModel.saveUserFoodItem(6, dataTransfer)
                }
                else
                {
                    val testdata = DataCompiler(nullNutrition,0)
                    sharedViewModel.saveUserFoodItem(6,testdata)
                }

            }

        })




        foodSaveButton!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {







            }
        })



    }

}
