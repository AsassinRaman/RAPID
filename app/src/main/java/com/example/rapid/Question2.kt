package com.example.rapid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityQuestion2Binding
import com.google.android.material.slider.Slider

class Question2 : AppCompatActivity() {
    lateinit var binding: ActivityQuestion2Binding
    private lateinit var severitySlider: Slider
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuestion2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent= Intent(this,Question3::class.java)
        binding.buttonNext.setOnClickListener{
            startActivity(intent)
            finish()
        }
        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Initialize the Slider
        severitySlider = findViewById(R.id.severity_slider)

        // Set the initial value from SharedPreferences if available
        val storedValue = sharedPreferences.getFloat("severitySliderValue", 0f)
        severitySlider.value = storedValue

        // Set listener to capture changes
        severitySlider.addOnChangeListener { slider, value, fromUser ->
            // Store the value in SharedPreferences
            storeSliderValue(value)
            // Optionally, display the value or handle it as needed
            Toast.makeText(this, "Selected value: $value", Toast.LENGTH_SHORT).show()
        }
    }
    private fun storeSliderValue(value: Float) {
        with(sharedPreferences.edit()) {
            putFloat("severitySliderValue", value)
            apply()
        }
    }

}