package com.example.rapid

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.rapid.databinding.ActivityPredictionBinding
import kotlin.random.Random



class Prediction : AppCompatActivity() {

    private val DELAY_TIME = Random.nextLong(1000, 5000)
    private lateinit var binding: ActivityPredictionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this, Result::class.java))
            finish()
        }, DELAY_TIME)


    }
}






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

//}

