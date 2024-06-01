package com.example.rapid

import AqiResponse
import WeatherResponse
import WeatherService
import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.rapid.databinding.ActivitySelectCityBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class SelectCity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private val LOADING_DURATION = Random.nextLong(5000, 10000)
    private val apiKey = "28fe549a5ff3df3902db31608079ed70"
    lateinit var binding: ActivitySelectCityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySelectCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        } else
        {
//            getLocationAndWeather()
            startLoading()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//                getLocationAndWeather()
                startLoading()
            } else {
                // Handle the case where permission was denied
                showPermissionDeniedDialog()
                // Optionally, you can show a message to the user or close the app
            }
        }
    }
    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Location Permission Required")
            .setMessage("This app requires location access to function properly. Please grant the permission in app settings.")
            .setPositiveButton("Retry") { dialog, which ->
                // Retry requesting permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
            .setNegativeButton("Exit") { dialog, which ->
                // Exit the app
                finish()
            }
            .show()



    }

    private fun startLoading() {
        Handler(Looper.getMainLooper()).postDelayed({
            // Start the next activity after 10 seconds
            val intent = Intent(this, Question1::class.java)
            startActivity(intent)
            finish()
        }, LOADING_DURATION)
    }

    @SuppressLint("MissingPermission")
    private fun getLocationAndWeather() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                fetchWeather(it.latitude, it.longitude)
                fetchAqi(it.latitude,it.longitude)
            }
        }
    }

    private fun fetchWeather(lat: Double, lon: Double) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=28fe549a5ff3df3902db31608079ed70")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val weatherCall = service.getCurrentWeather(lat, lon, apiKey)


        // Fetch Weather Data
        weatherCall.enqueue(object: Callback<WeatherResponse> {
            override fun onResponse(
                p0: retrofit2.Call<WeatherResponse>,
                p1: retrofit2.Response<WeatherResponse>
            ) {
                if (p1.isSuccessful) {
                    val weather = p1.body()
                    weather?.let {
                        // Store weather details locally or update UI
                        var intent=Intent(this@SelectCity,Prediction::class.java)
//                        intent.putExtra("name",it.name)
                        intent.putExtra("temp",it.main.temp)
                        intent.putExtra("humidity",it.main.humidity)
                        intent.putExtra("wind",it.wind.speed)
                        // Log the values to verify they are correct
                        Log.d("SelectCity", "name: ${it.name}, temp: ${it.main.temp}, humidity: ${it.main.humidity}, wind: ${it.wind.speed}")
                        startActivity(intent)  // Start the next activity
//                      println("Weather in ${it.name}: ${it.main.temp}°C, Humidity: ${it.main.humidity}%")
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable?) {
                if (t != null) {
                    t.printStackTrace()
                    Log.e("WeatherService", "Error fetching weather data", t)
                }
            }
        })


    }

    private fun fetchAqi(lat: Double, lon: Double) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=$lat&lon=$lon&appid=28fe549a5ff3df3902db31608079ed70")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val aqiCall = service.getAirQuality(lat, lon, apiKey)
        // Fetch AQI Data
        aqiCall.enqueue(object : Callback<AqiResponse> {
            override fun onResponse(
                p0: retrofit2.Call<AqiResponse>,
                p1: retrofit2.Response<AqiResponse>
            ) {
                if (p1.isSuccessful) {
                    val aqiResponse = p1.body()
                    aqiResponse?.let {
                        val aqi = it.list.firstOrNull()?.main?.aqi
                        println("AQI at location: $aqi")
                    }
                }
            }

            override fun onFailure(call: Call<AqiResponse>, t: Throwable) {
                t.printStackTrace()
                Log.e("WeatherService", "Error fetching AQI data", t)
            }
        })
        }

}

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//                LOCATION_PERMISSION_REQUEST_CODE
//            )
//        } else {
//            startLoading()
//        }
