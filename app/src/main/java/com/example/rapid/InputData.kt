package com.example.rapid

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityInputDataBinding

class InputData : AppCompatActivity() {
    private lateinit var binding: ActivityInputDataBinding
    private lateinit var valueTextView: TextView
    private lateinit var valueTextView2: TextView
    private lateinit var valueTextView3: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = intent.getStringExtra("name")
        val temp = intent.getDoubleExtra("temp", 89.0)
        val humidity = intent.getIntExtra("humidity", 12)
        val windSpeed = intent.getDoubleExtra("wind", 11.0)



//        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val tempTextView = findViewById<TextView>(R.id.AQI)
        val humidityTextView = findViewById<TextView>(R.id.Hum)
        val windTextView = findViewById<TextView>(R.id.Wind)

//        nameTextView.text = "City: $name"
        tempTextView.text = "AQI: $temp"
        humidityTextView.text = "Humidity: $humidity%"
        windTextView.text = "Wind Speed: $windSpeed m/s"


        //Question 1
        // Initialize the TextView
        valueTextView = findViewById(R.id.itching)

        // Retrieve the stored value from SharedPreferences
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val storedValue = sharedPreferences.getFloat("severitySliderValue", 0f)

        // Map the numerical value to the corresponding descriptive string
        val severityDescription = when (storedValue.toInt()) {
            0 -> "None"
            1 -> "Mild"
            2 -> "Severe"
            else -> "Unknown"
        }
        // Display the value
        valueTextView.text = "Itchy Nose: $severityDescription"

        //Question 2
        // Initialize the TextView
        valueTextView2 = findViewById(R.id.runny)

        // Retrieve the stored value from SharedPreferences
        val sharedPreferences2 = getSharedPreferences("MyAppPreferences2", Context.MODE_PRIVATE)
        val storedValue2 = sharedPreferences2.getFloat("severitySlider2Value", 0f)

        // Map the numerical value to the corresponding descriptive string
        val severityDescription2 = when (storedValue2.toInt()) {
            0 -> "None"
            1 -> "Mild"
            2 -> "Severe"
            else -> "Unknown"
        }
        // Display the value
        valueTextView2.text = "Runny Nose: $severityDescription2"

        //Question 3
        // Initialize the TextView
        valueTextView3 = findViewById(R.id.sneezing)

        // Retrieve the stored value from SharedPreferences
        val sharedPreferences3 = getSharedPreferences("MyAppPreferences3", Context.MODE_PRIVATE)
        val storedValue3 = sharedPreferences3.getFloat("severitySlider3Value", 0f)

        // Map the numerical value to the corresponding descriptive string
        val severityDescription3 = when (storedValue3.toInt()) {
            0 -> "None"
            1 -> "Mild"
            2 -> "Severe"
            else -> "Unknown"
        }
        // Display the value
        valueTextView3.text = "Sneezing: $severityDescription3"

    }





}
