package com.example.test.mealPlanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareFoodModel:ViewModel() {

    private var foodItem1 = MutableLiveData<DataCompiler>(null)
    val userFoodItem1:LiveData<DataCompiler> = foodItem1
    private var foodItem2 = MutableLiveData<DataCompiler>(null)
    val userFoodItem2:LiveData<DataCompiler> = foodItem2
    private var foodItem3 = MutableLiveData<DataCompiler>(null)
    val userFoodItem3:LiveData<DataCompiler> = foodItem3
    private var foodItem4 = MutableLiveData<DataCompiler>(null)
    val userFoodItem4:LiveData<DataCompiler> = foodItem4
    private var foodItem5 = MutableLiveData<DataCompiler>(null)
    val userFoodItem5:LiveData<DataCompiler> = foodItem5
    private var foodItem6 = MutableLiveData<DataCompiler>(null)
    val userFoodItem6:LiveData<DataCompiler> = foodItem6
    private var foodItem7 = MutableLiveData<DataCompiler>(null)
    val userFoodItem7:LiveData<DataCompiler> = foodItem7


    fun saveUserFoodItem(number:Int,userData:DataCompiler){

        when(number)
        {
            0 ->{foodItem1.value = userData}
            1 ->{foodItem2.value = userData}
            2 ->{foodItem3.value = userData}
            3 ->{foodItem4.value = userData}
            4 ->{foodItem5.value = userData}
            5 ->{foodItem6.value = userData}
            6 ->{foodItem7.value = userData}
        }

    }

    fun fullReset()
    {
        foodItem1 = MutableLiveData<DataCompiler>(null)
        foodItem2 = MutableLiveData<DataCompiler>(null)
        foodItem3 = MutableLiveData<DataCompiler>(null)
        foodItem4 = MutableLiveData<DataCompiler>(null)
        foodItem5 = MutableLiveData<DataCompiler>(null)
        foodItem6 = MutableLiveData<DataCompiler>(null)
        foodItem7 = MutableLiveData<DataCompiler>(null)

    }

}