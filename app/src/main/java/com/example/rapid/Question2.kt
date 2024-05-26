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
    private lateinit var severitySlider2: Slider
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
        severitySlider2 = findViewById(R.id.severity_slider2)

        // Set the initial value from SharedPreferences if available
        val storedValue2 = sharedPreferences.getFloat("severitySliderValue", 0f)
        severitySlider2.value = storedValue2

        // Set listener to capture changes
        severitySlider2.addOnChangeListener { slider, value, fromUser ->
            // Store the value in SharedPreferences
            storeSliderValue2(value)
            // Optionally, display the value or handle it as needed
            Toast.makeText(this, "Selected value: $value", Toast.LENGTH_SHORT).show()
        }
    }
    private fun storeSliderValue2(value: Float) {
        with(sharedPreferences.edit()) {
            putFloat("severitySliderValue", value)
            apply()
        }
    }

}