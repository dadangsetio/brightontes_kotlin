package com.repairzone.brightontes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.databinding.ObservableArrayList
import com.repairzone.brightontes.data.model.Movie
import com.repairzone.brightontes.ui.MainViewModel
import com.repairzone.brightontes.ui.movie.details.MovieDetailActivity
import com.repairzone.brightontes.ui.widget.ObservableListChangeCallback
import com.repairzone.brightontes.ui.widget.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val moviesModel = ObservableArrayList<Movie>()
    private val movieAdapter by lazy {
        RecyclerViewAdapter<Movie>(
            moviesModel,
            R.layout.item_movie,
            this::onItemClicked
        )
    }

    private fun onItemClicked(type: Int, movie: Movie, i: Int) {
        when(type){
            movie.actionOpen -> {
                val intent = Intent(this, MovieDetailActivity::class.java)
                intent.putExtra(MovieDetailActivity.EXTRA_ID, movie.imdbID)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val search = menu?.findItem(R.id.nav_search)
        val favorit = menu?.findItem(R.id.nav_favorit)
        val searchView = search?.actionView as SearchView
        onSearchView(searchView)
        favorit?.setOnMenuItemClickListener { _ ->
            showFavoritFragment()
            println("bisaa")
            return@setOnMenuItemClickListener true
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun showFavoritFragment(){
        val fragment = FavoritFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
    }

    private fun onSearchView(searchView: SearchView){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getMovieBySearch(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        setupMovieRecycler()

    }

    private fun setupMovieRecycler(){
        moviesModel.addOnListChangedCallback(ObservableListChangeCallback<Movie>(movieAdapter))
        viewModel.movies.observe(this){
            if (it != null){
                moviesModel.clear()
                moviesModel.addAll(it)
            }
        }
        rv_movie.apply {
            adapter = movieAdapter
        }
    }
}