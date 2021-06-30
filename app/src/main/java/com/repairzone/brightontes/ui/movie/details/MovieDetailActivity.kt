package com.repairzone.brightontes.ui.movie.details

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.repairzone.brightontes.R
import com.repairzone.brightontes.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra-id"
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.content.lifecycleOwner = this
        binding.content.viewModel = viewModel

        setupDetail()

        setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Loved", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    fun setupDetail(){
        val id  = intent.getStringExtra(EXTRA_ID)
        viewModel.getDetail(id)
        viewModel.title.observe(this){
            binding.toolbar.title = it
        }
        viewModel.poster.observe(this){
            if (!it.equals("") || it != null || it != ""){
                val options = RequestOptions().transform(CenterCrop())
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                Glide.with(this)
                    .load(it)
                    .apply(options)
                    .centerCrop()
                    .into(binding.ivPoster)
            }
        }
    }
}