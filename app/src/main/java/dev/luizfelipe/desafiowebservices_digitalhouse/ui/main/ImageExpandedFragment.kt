package dev.luizfelipe.desafiowebservices_digitalhouse.ui.main

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import dev.luizfelipe.desafiowebservices_digitalhouse.R
import dev.luizfelipe.desafiowebservices_digitalhouse.models.Image
import dev.luizfelipe.desafiowebservices_digitalhouse.models.Result

class ImageExpandedFragment : DialogFragment() {
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
        val root = inflater.inflate(R.layout.fragment_image_expanded, container, false)

        val imgCover: ImageView = root.findViewById(R.id.img_cover)
        val close : ImageButton = root.findViewById(R.id.closeBtn)

        if (comic.images.isNotEmpty()) {
            Glide
                .with(root)
                .load("${comic.images[0].path}.${comic.images[0].extension}")
                .into(imgCover)
        }

        close.setOnClickListener {
            dismiss()
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(comic: Result) =
            ImageExpandedFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("COMIC", comic)
                }
            }
    }
}