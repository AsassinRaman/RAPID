package com.example.rapid

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rapid.databinding.ActivityQuestion1Binding
import com.example.rapid.databinding.ActivityQuestion3Binding

class Question3 : AppCompatActivity() {
    lateinit var binding: ActivityQuestion3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuestion3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent= Intent(this,Prediction::class.java)
        binding.buttonNext.setOnClickListener{
            startActivity(intent)
            finish()
        }
    }
}