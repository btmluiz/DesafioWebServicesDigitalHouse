package dev.luizfelipe.desafiowebservices_digitalhouse.interfaces

import android.view.View
import dev.luizfelipe.desafiowebservices_digitalhouse.models.Result

interface ComicClick {
    fun onComicClickListener(comic: Result): View.OnClickListener
}