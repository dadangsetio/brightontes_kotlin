package com.repairzone.brightontes.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.repairzone.brightontes.data.model.Movie
import com.repairzone.brightontes.data.repository.MovieRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    companion object{
        const val TAG = "MainAct"
    }
    val movies = MutableLiveData<List<Movie>>()
    private val movieRepository = MovieRepository(application)
    fun getMovieBySearch(title: String){
        movieRepository.getMovie(title){ list, error ->
            if (error == null){
                movies.postValue(list)
            }else{
                Log.e(TAG, "getMovieBySearch: ",error )
            }
        }
    }
}