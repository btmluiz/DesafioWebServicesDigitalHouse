package dev.luizfelipe.desafiowebservices_digitalhouse.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.luizfelipe.desafiowebservices_digitalhouse.R
import dev.luizfelipe.desafiowebservices_digitalhouse.adapters.ComicsAdapter
import dev.luizfelipe.desafiowebservices_digitalhouse.interfaces.ComicClick
import dev.luizfelipe.desafiowebservices_digitalhouse.models.Result
import dev.luizfelipe.desafiowebservices_digitalhouse.services.repo
import dev.luizfelipe.desafiowebservices_digitalhouse.utils.Utils

class MainActivity : AppCompatActivity(), ComicClick {

    private val viewModel by viewModels<MainViewModel>() {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(repo) as T
            }
        }
    }

    private lateinit var rc: RecyclerView
    private val listComic = mutableListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rc = findViewById(R.id.recyclerComics)
        val adapter = ComicsAdapter(this, listComic, this)
        val spanCount = Utils.calculateSpan(this, 124)
        val manager = GridLayoutManager(this, spanCount)
        rc.adapter = adapter
        rc.layoutManager = manager

        viewModel.listComic.observe(this){
            listComic.addAll(it)
            adapter.notifyDataSetChanged()
        }

//        rc.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//
//                val count = adapter.itemCount
//
//                val v_item = manager.findFirstCompletelyVisibleItemPosition()
//
//                if (v_item == adapter.itemCount-1) {
//                    if (count < viewModel.totalComics.value!!) {
//                        viewModel.popListComics(count + 20)
//                    }
//                }
//            }
//        })

        viewModel.popListComics()
    }

    override fun onComicClickListener(comic: Result): View.OnClickListener = View.OnClickListener {
        val t = supportFragmentManager.beginTransaction()
        val frag: DialogFragment = DescriptionFragment.newInstance(comic)
        frag.show(t, "Comic")
    }
}