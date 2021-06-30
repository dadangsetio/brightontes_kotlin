package com.repairzone.brightontes.ui.movie.favorit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.ObservableArrayList
import com.repairzone.brightontes.R
import com.repairzone.brightontes.data.model.Movie
import com.repairzone.brightontes.ui.widget.RecyclerViewAdapter

class FavoritActivity : AppCompatActivity() {
    private val movieModel = ObservableArrayList<Movie>()
    private val movieAdapter by lazy {
        RecyclerViewAdapter<Movie>(
            movieModel,
            R.layout.item_movie,
            this::onItemClicked
        )
    }

    private fun onItemClicked(type: Int, movie: Movie, i: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorit)
    }
}