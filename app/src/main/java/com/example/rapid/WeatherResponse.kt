data class WeatherResponse(
    val main: Main,
    val wind: Wind,
    val name: String
)

data class Main(
    val temp: Float,
    val humidity: Int
)

data class Wind(
    val speed: Float
)

//data class aqi(
//    val index: Float
//)
