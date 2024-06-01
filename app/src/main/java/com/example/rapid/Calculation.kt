enum class Severity {
    NONE, MILD, SEVERE
}

data class AllergyFactors(
    val AQI: Int,
    val windSpeed: Int,
    val humidity: Int,
    val treePollen: Severity,
    val grassPollen: Severity,
    val ragweedPollen: Severity,
    val itchyNose: Severity,
    val runnyNose: Severity,
    val sneezing: Severity
)


fun calculateAllergyLevel(factors: AllergyFactors): Severity {
    val (AQI, windSpeed, humidity, treePollen, grassPollen, ragweedPollen, itchyNose, runnyNose, sneezing) = factors

    // Weights for symptoms
    val symptomSeverityScore = mapOf(
        Severity.NONE to 0,
        Severity.MILD to 1,
        Severity.SEVERE to 2
    )

    // Weights for pollen levels
    val pollenSeverityScore = mapOf(
        Severity.NONE to 0,
        Severity.MILD to 1,
        Severity.SEVERE to 2
    )

    // Calculate symptom score
    val symptomScore = symptomSeverityScore[itchyNose]!! + symptomSeverityScore[runnyNose]!! + symptomSeverityScore[sneezing]!!

    // Calculate pollen score
    val pollenScore = pollenSeverityScore[treePollen]!! + pollenSeverityScore[grassPollen]!! + pollenSeverityScore[ragweedPollen]!!

    // Calculate environment score based on AQI, windSpeed, and humidity
    val environmentScore = when {
        AQI > 100 -> 3
        AQI in 51..100 -> 2
        else -> 1
    } + when {
        windSpeed > 20 -> 2
        windSpeed in 10..20 -> 1
        else -> 0
    } + when {
        humidity < 30 -> 2
        humidity in 30..60 -> 1
        else -> 0
    }

    // Total score calculation
    val totalScore = symptomScore + pollenScore + environmentScore

    // Determine final allergy level
    return when {
        totalScore >= 12 -> Severity.SEVERE
        totalScore in 6..11 -> Severity.MILD
        else -> Severity.NONE
    }
}

fun main() {


    val factors = AllergyFactors(
        AQI = 200,
        windSpeed = 15,
        humidity = 75,
        treePollen = Severity.NONE,
        grassPollen = Severity.MILD,
        ragweedPollen = Severity.NONE,
        itchyNose = Severity.NONE,
        runnyNose = Severity.NONE,
        sneezing = Severity.NONE,
    )

    val allergyLevel = calculateAllergyLevel(factors)
    println("Predicted Allergy Level: $allergyLevel")
//    var intent= Intent(this@Calculation, Result::class.java)
//    intent.putExtra("AllergyPrediction",allergyLevel)
}
