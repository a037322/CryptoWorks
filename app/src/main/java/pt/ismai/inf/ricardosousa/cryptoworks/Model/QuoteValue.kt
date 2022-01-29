package pt.ismai.inf.ricardosousa.cryptoworks.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class QuoteValue(
    var price: Double,
    var percent_change_1h: Double,
    var percent_change_24h: Double,
    var percent_change_7d: Double
)
