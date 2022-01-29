package pt.ismai.inf.ricardosousa.cryptoworks.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CryptoWorksResponse(var data: List<CoinModel>)