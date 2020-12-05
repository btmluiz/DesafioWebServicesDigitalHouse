package dev.luizfelipe.desafiowebservices_digitalhouse.ui.main

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import dev.luizfelipe.desafiowebservices_digitalhouse.R
import dev.luizfelipe.desafiowebservices_digitalhouse.models.Result
import java.text.SimpleDateFormat

class DescriptionFragment : DialogFragment() {
    private lateinit var comic: Result
    private lateinit var ctx: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            comic = it.getSerializable("COMIC") as Result
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is MainActivity) ctx = context
    }

    override fun onStart() {
        super.onStart()

        val parameter = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(parameter, parameter)
        dialog?.window?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    ctx,
                    R.color.background
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_description, container, false)

        val image : ImageView = root.findViewById(R.id.image_comic)
        val imageThumb : ImageView = root.findViewById(R.id.thumb)
        val title : TextView = root.findViewById(R.id.title_comic)
        val description: TextView = root.findViewById(R.id.description)
        val published: TextView = root.findViewById(R.id.dt_published)
        val price: TextView = root.findViewById(R.id.price_vl)
        val pages: TextView = root.findViewById(R.id.pages_qtd)
        val backButton: ImageButton = root.findViewById(R.id.back_button)

        backButton.setOnClickListener {
            dismiss()
        }

        if (comic.images.isNotEmpty()) {
            Glide
                .with(root)
                .load("${comic.images[0].path}.${comic.images[0].extension}")
                .into(image)

            Glide
                .with(root)
                .load("${comic.images[comic.images.size - 1].path}.${comic.images[comic.images.size - 1].extension}")
                .into(imageThumb)
        }

        image.setOnClickListener {
            val t = ctx.supportFragmentManager.beginTransaction()
            val frag : DialogFragment = ImageExpandedFragment.newInstance(comic)
            frag.show(t, "Expanded")
        }

        title.text = comic.title

        if (!comic.description.isNullOrEmpty())
            description.text = comic.description
        else
            description.text = "Description not Avaliable"

        val dateFormat = SimpleDateFormat("MMMM dd, YYYY")
        published.text = dateFormat.format(comic.modified)
        price.text = "$ " + comic.prices[0].price.toString()
        pages.text = comic.pageCount.toString()

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(comic: Result) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("COMIC", comic)
                }
            }
    }
}