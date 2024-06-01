package com.example.rapid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityPrecautionsMildBinding

class PrecautionsMild : AppCompatActivity() {
    private lateinit var binding: ActivityPrecautionsMildBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrecautionsMildBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}