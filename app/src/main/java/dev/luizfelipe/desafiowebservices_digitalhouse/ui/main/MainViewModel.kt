package dev.luizfelipe.desafiowebservices_digitalhouse.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.luizfelipe.desafiowebservices_digitalhouse.interfaces.API
import dev.luizfelipe.desafiowebservices_digitalhouse.models.RequestComic
import dev.luizfelipe.desafiowebservices_digitalhouse.models.Result
import dev.luizfelipe.desafiowebservices_digitalhouse.services.Repository
import dev.luizfelipe.desafiowebservices_digitalhouse.utils.Utils
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainViewModel(private val repository: Repository): ViewModel(), API {
    var listComic = MutableLiveData<List<Result>>()
    val totalComics = MutableLiveData<Int>()

    fun popListComics(c_offset: Int = 0) {
        val dateTime = Date().toString()
        viewModelScope.launch {
            val call = repository.getComics(
                publicKey,
                Utils.hashFormat(privateKey, publicKey, dateTime),
                dateTime,
                offset = c_offset
            )

            call.enqueue(object : Callback<RequestComic> {
                override fun onResponse(
                    call: Call<RequestComic>,
                    response: Response<RequestComic>
                ) {
                    response.body().let {
                        if (it != null) {
                            listComic.value = it.data.results
                            totalComics.value = it.data.total
                        }
                    }
                }

                override fun onFailure(call: Call<RequestComic>, t: Throwable) {
                    Log.i("Request", t.toString())
                }
            })
        }
    }
}