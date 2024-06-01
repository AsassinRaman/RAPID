package com.example.rapid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityPrecautionsSevereBinding

class PrecautionsSevere : AppCompatActivity() {
    private lateinit var binding: ActivityPrecautionsSevereBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrecautionsSevereBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}