package com.example.foodsuggestion

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var inputMeal: EditText
    private lateinit var mealResult: TextView
    private lateinit var btnGen: Button
    private lateinit var btnClear: Button
    private lateinit var btnExit: Button


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inputMeal = findViewById(R.id.inputMeal)
        mealResult = findViewById(R.id.mealResult)
        btnGen = findViewById(R.id.btnGen)
        btnClear = findViewById(R.id.btnClear)
        btnExit = findViewById(R.id.btnExit)

        // These are the food options for the different meals
        val breakfastOption = listOf("Cereal", "Omelette", "Oatmeal", "Muesli", "Porridge")
        val lunchOption = listOf("Sandwiches", "Pie", "HotDog", "Wrap", "Burger", "Kota" )
        val snackOption = listOf("Packet of chips", "Fruits", "Popcorn", "Packet of sweets")
        val dinnerOption = listOf("Braai", "Chicken Curry", "Beef Stew", "Pizza", "Roasted Chicken")
        val dessertOption = listOf("SLice of cake", "Cupcake", "Malva pudding", "Cheesecake", "Tiramisu")

        // Gives out food options based on what the user puts in
        btnGen.setOnClickListener{
            val mealType = inputMeal.text.toString().trim().lowercase()

            val foodSuggestion = when (mealType) {
                "Breakfast", "breakfast", "Morning", "morning" -> breakfastOption.random()
                "Lunch", "lunch", "Afternoon", "afternoon" -> lunchOption.random()
                "Light snack", "Snack", "light snack", "snack" -> snackOption.random()
                "Dinner", "dinner", "Night", "night", "Evening", "evening", "Supper", "supper" -> dinnerOption.random()
                "Dessert", "dessert" -> dessertOption.random()

                // In case the user doesn't input any of the words above
                else -> {
                    Toast.makeText(this, "Please enter 'time of day' or 'meal type'", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                }
            }
            // Gives the result of meal
            mealResult.text = "You should have: $foodSuggestion"
        }

        // Clears the editText and textView
        btnClear.setOnClickListener{
            inputMeal.text.clear()
            mealResult.text = ""
        }
        // User will be able to exit the app
        btnExit.setOnClickListener{
            finishAffinity()
            exitProcess(1)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}