package com.example.rapid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityPrecautionsNoneBinding

class PrecautionsNone : AppCompatActivity() {
    private lateinit var binding: ActivityPrecautionsNoneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrecautionsNoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}