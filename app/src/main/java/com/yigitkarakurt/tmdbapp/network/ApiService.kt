package com.yigitkarakurt.tmdbapp.network

import com.yigitkarakurt.tmdbapp.model.MovieDetailResponse
import com.yigitkarakurt.tmdbapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("popular")
    suspend fun getMovieList(
        @Header("Authorization") token:String
    ): Response<MovieResponse>

    @GET("{movieId}")
    suspend fun getMovieDetail(
        @Header("Authorization") token:String,
        @Path("movieId") movieId : String
    ): Response<MovieDetailResponse>
}