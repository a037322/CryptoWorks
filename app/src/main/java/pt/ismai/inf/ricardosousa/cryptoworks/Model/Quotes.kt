package pt.ismai.inf.ricardosousa.cryptoworks.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quotes(@JsonProperty("USD") var quoteUsd: QuoteValue)