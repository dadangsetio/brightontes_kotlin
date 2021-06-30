package com.repairzone.brightontes.ui.movie.details

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.repairzone.brightontes.data.model.MovieDetail
import com.repairzone.brightontes.data.repository.MovieRepository

class MovieDetailViewModel(application: Application): AndroidViewModel(application) {
    val movieDetail = MutableLiveData<MovieDetail>()
    val title = MutableLiveData<String>()
    val poster = MutableLiveData<String>()
    private val movieRepository = MovieRepository(application)

    companion object{
        const val TAG = "MovieDetailVM"
    }

    fun getDetail(
        id: String?
    ){
        movieRepository.getMovieDetail(id!!){ movieDetail, error ->
            if (error == null){
                title.postValue(movieDetail?.title)
                poster.postValue(movieDetail?.poster)
                this.movieDetail.postValue(movieDetail)
            }else{
                Log.e(TAG, "getDetail: ", error)
            }
        }
    }

}