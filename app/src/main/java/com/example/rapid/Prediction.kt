package com.example.rapid

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.rapid.Question1
import com.example.rapid.R
import com.example.rapid.databinding.ActivityPredictionBinding
import com.example.rapid.databinding.ActivityResultBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Result
import kotlin.random.Random

class Prediction : AppCompatActivity() {

    private val DELAY_TIME= Random.nextLong(1000, 5000)

    private lateinit var binding: ActivityPredictionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this, com.example.rapid.Result::class.java))
            finish()
        }, DELAY_TIME)

//        fun startLoading() {
//            Handler(Looper.getMainLooper()).postDelayed({
//                // Move to the next activity
//                val intent = Intent(this, Result::class.java)
//                startActivity(intent)
//            }, 5000)
//        }
//        startLoading()


//        CoroutineScope(Dispatchers.Main).launch {
//            // Delay of 5 seconds
//            delay(5000)
//            // Move to the next activity
//            val intent = Intent(this@Prediction, Result::class.java)
//            startActivity(intent)
//            finish()
//        }


    }
}
