package dev.luizfelipe.desafiowebservices_digitalhouse.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Price(
    @SerializedName("price")
    val price: Double,
    @SerializedName("type")
    val type: String
) : Serializable