package com.example.rapid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityResultBinding
import kotlin.random.Random

class Result : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var valueTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnYourData.setOnClickListener{
            startActivity(Intent(this,InputData::class.java))
        }


//        var itching=intent.getFloatExtra("itching",0.1f)
//        binding.txtItching.setText("itching")


        // List of options
        val options = listOf("None", "Mild", "Severe")

        // Generate a random index within the range of options list
        val randomIndex = Random.nextInt(0, options.size-1)


        // Select and display the random option
        val selectedOption = options[randomIndex]
        binding.txtResult.setText(selectedOption)

        // Initialize the TextView
        valueTextView = findViewById(R.id.txt_itching)

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
        valueTextView.text = "Stored value: $severityDescription"

    }

}