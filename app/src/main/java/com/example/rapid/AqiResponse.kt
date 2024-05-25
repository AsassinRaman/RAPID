data class AqiResponse(
    val list: List<AqiData>
)

data class AqiData(
    val main: AqiMain
)

data class AqiMain(
    val aqi: Int
)
