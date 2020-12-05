package dev.luizfelipe.desafiowebservices_digitalhouse.services

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dev.luizfelipe.desafiowebservices_digitalhouse.models.RequestComic
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Repository {
    @GET("comics")
    fun getComics(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("title") title: String? = null,
        @Query("titleStartsWith") titleStartWith : String? = null,
        @Query("characters") charactersList : List<Int>? = null,
        @Query("offset") offset : Int? = null
    ): Call<RequestComic>
}

val url = "https://gateway.marvel.com:443/v1/public/"

val gson = GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
    .create()

val retrofit = Retrofit.Builder()
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

val repo = retrofit.create(Repository::class.java)