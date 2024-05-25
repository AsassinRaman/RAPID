package com.example.rapid
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityWelcomeBinding

class Welcome : AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var intent= Intent(this,SelectCity::class.java)
        binding.buttonTakeTest.setOnClickListener{
            startActivity(intent)
            finish()
        }


    }
}
