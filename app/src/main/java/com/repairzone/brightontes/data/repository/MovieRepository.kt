package com.repairzone.brightontes.data.repository

import android.content.Context
import com.repairzone.brightontes.data.model.Movie
import com.repairzone.brightontes.data.model.MovieDetail
import com.repairzone.brightontes.data.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository(
    context: Context
) {

    private val api = ApiClient.getClient(context)
    private val apiKey = "a52de6ba"

    fun getMovie(name: String, callback: (List<Movie>?, Error?) -> Unit) =
        api.getMovieSearch(
            searchName = name,
            apikey = apiKey,
            page = 1
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when{
                    it.isSuccessful -> {
                        val body = it.body() ?: return@subscribe
                        callback(body.Search,  null)
                    }
                    else -> {
                        callback(null, Error(it.message()))
                    }
                }
            }, {
                callback(null, Error(it))
            })

    fun getMovieDetail(
        imdbId: String,
        callback: (MovieDetail?, Error?) -> Unit
    ) = api.getMOvieDetails(imdbId, apiKey)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
                   when{
                       it.isSuccessful -> {
                           val body = it.body() ?: return@subscribe
                           callback(body, null)
                       }
                       else -> {
                           callback(null, Error(it.message()))
                       }
                   }
        },{
            callback(null, Error(it))
        })

}