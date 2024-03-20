package com.example.test.mealPlanner

class Nutrition {
    private var Name:String
    private var Calories:Int
    private var Sodium:Int
    private var Carbohydrate:Int
    private var Fats:Int
    private var Protein:Int

    constructor(
        Name: String,
        Calories: Int,
        Sodium: Int,
        Carbohydrate: Int,
        Fats: Int,
        Protein: Int
    ) {
        this.Name = Name
        this.Calories = Calories
        this.Sodium = Sodium
        this.Carbohydrate = Carbohydrate
        this.Fats = Fats
        this.Protein = Protein
    }

//Getter for the vars
    fun getName():String
    {
        return Name
    }
    fun getCalories():Int
    {
        return Calories
    }
    fun getSodium():Int
    {
        return Sodium
    }
    fun getCarbohydrate():Int
    {
        return Carbohydrate
    }
    fun getFats():Int
    {
        return Fats
    }
    fun getProtein():Int
    {
        return Protein
    }
//Setters for the vars
    fun setName(names:String)
    {
        Name = names
    }
    fun setCalories(num1:Int)
    {
        Calories = num1
    }
    fun setSodium(num2:Int)
    {
        Sodium = num2
    }
    fun setCarbohydrate(num3:Int)
    {
        Carbohydrate = num3
    }
    fun setFats(num4:Int)
    {
        Fats = num4
    }
    fun setProtein(num5:Int)
    {
        Protein = num5
    }

}

//new class for compiling
class DataCompiler
{
    private var foodItemNamed:String = ""
    private var foodQuantity:Int = 0
    constructor(foodItemNamed: String, foodQuantity: Int) {
        this.foodItemNamed = foodItemNamed
        this.foodQuantity = foodQuantity
    }

    fun getFoodName():String
    {
        return foodItemNamed
    }

    fun getFoodQuantity():Int
    {
        return foodQuantity
    }

}