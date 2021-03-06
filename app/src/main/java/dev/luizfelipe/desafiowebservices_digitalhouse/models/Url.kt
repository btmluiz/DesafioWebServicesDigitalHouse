package dev.luizfelipe.desafiowebservices_digitalhouse.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Url(
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
) : Serializable