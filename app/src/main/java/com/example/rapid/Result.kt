package com.example.rapid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityResultBinding

//enum class Severity {
//    NONE, MILD, SEVERE
//}

class Result : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var txtResult: TextView
    private lateinit var btnPrecaution: Button
//    private lateinit var btnYourData: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // List of options
//        val options = listOf("None", "Mild", "Severe")
//
//        // Generate a random index within the range of options list
//        val randomIndex = Random.nextInt(0, options.size)
//
//
//        // Select and display the random option
//        val selectedOption = options[randomIndex]

        val question1Severity = intent.getFloatExtra("Question1Severity", 0f)
        val question2Severity = intent.getFloatExtra("Question2Severity", 0f)
        val question3Severity = intent.getFloatExtra("Question3Severity", 0f)

        // Convert severity values to a prediction result
        val result = calculatePrediction(question1Severity, question2Severity, question3Severity)
        binding.txtResult.text = result

       // Other initialization code...


//        binding.txtResult.setText(selectedOption)
        txtResult = findViewById(R.id.txt_result)

        binding.btnYourData.setOnClickListener{
            startActivity(Intent(this,InputData::class.java))
        }

        btnPrecaution = findViewById(R.id.btnPrecautions)
        btnPrecaution.setOnClickListener {
            val result = txtResult.text.toString()

            when (result.lowercase()) {
                "none" -> {
                    // Navigate to NoneActivity
                    val intent = Intent(this, PrecautionsNone::class.java)
                    startActivity(intent)
                }
                "mild" -> {
                    // Navigate to MildActivity
                    val intent = Intent(this, PrecautionsMild::class.java)
                    startActivity(intent)
                }
                else -> {
                    // Navigate to OtherActivity
                    val intent = Intent(this, PrecautionsSevere::class.java)
                    startActivity(intent)
                }
            }
        }


    }
    private fun calculatePrediction(q1: Float, q2: Float, q3: Float): String {
        val totalSeverity = q1 + q2 + q3
        return when {
            totalSeverity == 0f -> "None"
            totalSeverity <= 1f -> "Mild"
            else -> "Severe"
        }


//        var itching=intent.getFloatExtra("itching",0.1f)
//        binding.txtItching.setText("itching")




}
}