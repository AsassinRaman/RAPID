package com.example.rapid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityQuestion3Binding
import com.google.android.material.slider.Slider

class Question3 : AppCompatActivity() {
    lateinit var binding: ActivityQuestion3Binding
    private lateinit var severitySlider3: Slider
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuestion3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent= Intent(this,Prediction::class.java)
        binding.buttonNext.setOnClickListener{
            startActivity(intent)
            finish()
        }
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Initialize the Slider
        severitySlider3 = findViewById(R.id.severity_slider3)

        // Set the initial value from SharedPreferences if available
        val storedValue3 = sharedPreferences.getFloat("severitySliderValue", 0f)
        severitySlider3.value = storedValue3

        // Set listener to capture changes
        severitySlider3.addOnChangeListener { slider, value, fromUser ->
            // Store the value in SharedPreferences
            storeSliderValue3(value)
            // Optionally, display the value or handle it as needed
            Toast.makeText(this, "Selected value: $value", Toast.LENGTH_SHORT).show()
        }
    }
    private fun storeSliderValue3(value: Float) {
        with(sharedPreferences.edit()) {
            putFloat("severitySliderValue", value)
            apply()
        }
    }

}