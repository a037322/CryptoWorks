package pt.ismai.inf.ricardosousa.cryptoworks.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinModel(
    var id: String,
    var name: String,
    var symbol: String,
    var quote: Quotes
) : Serializable