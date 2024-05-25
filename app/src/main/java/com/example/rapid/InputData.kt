package com.example.rapid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityInputDataBinding

class InputData : AppCompatActivity() {
    private lateinit var binding: ActivityInputDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityInputDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var wind=intent.getFloatExtra("wind",0.1f)
       var name= intent.getStringExtra("name")
        var temp=intent.getFloatExtra("temp",0.1f)
        var humidity=intent.getIntExtra("humidity",1)


        binding.txtData.setText("${wind}, ${name}, ${temp},${humidity}")

    }
}