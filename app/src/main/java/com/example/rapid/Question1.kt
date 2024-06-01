package com.example.rapid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityQuestion1Binding
import com.google.android.material.slider.Slider

class Question1 : AppCompatActivity() {
    lateinit var binding: ActivityQuestion1Binding
    private lateinit var severitySlider: Slider
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestion1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonNext.setOnClickListener {
        }

        var intent = Intent(this, Question2::class.java)
        binding.buttonNext.setOnClickListener {
            startActivity(intent)
            finish()
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Initialize the Slider
        severitySlider = findViewById(R.id.severity_slider1)

        // Set the initial value from SharedPreferences if available
        val storedValue = sharedPreferences.getFloat("severitySliderValue", 0f)
        severitySlider.value = storedValue

        // Set listener to capture changes
        severitySlider.addOnChangeListener { slider, value, fromUser ->
            // Store the value in SharedPreferences
            storeSliderValue(value)
            // Optionally, display the value or handle it as needed
            Toast.makeText(this, "Selected value: $value", Toast.LENGTH_SHORT).show()

            intent.putExtra("Ques1",value)
        }

        binding.buttonNext.setOnClickListener {
            val severityValue = severitySlider.value
            val intent = Intent(this, Question2::class.java)
            intent.putExtra("Question1Severity", severityValue)
            startActivity(intent)
            finish()
        }
    }


    private fun storeSliderValue(value: Float) {
        with(sharedPreferences.edit()) {
            putFloat("severitySliderValue", value)
            apply()
        }
    }

//        var tvResult: TextView = findViewById(R.id.txt_result)
//
//        val severity_slider: Slider = findViewById(R.id.severity_slider)
//        severity_slider.addOnChangeListener { slider, value, fromUser ->
//            tvResult.text = value.toString()
//
//
//        }

}