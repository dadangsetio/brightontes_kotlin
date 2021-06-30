package com.repairzone.brightontes.data.network

import com.repairzone.brightontes.data.model.MovieDetail
import com.repairzone.brightontes.data.model.SearchResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/")
    fun getMovieSearch(
        @Query("s") searchName: String,
        @Query("apikey") apikey: String,
        @Query("page") page: Int
    ): Observable<Response<SearchResponse>>

    @GET("/")
    fun getMOvieDetails(
        @Query("i") id: String,
        @Query("apikey") apikey: String,
    ): Observable<Response<MovieDetail>>
}