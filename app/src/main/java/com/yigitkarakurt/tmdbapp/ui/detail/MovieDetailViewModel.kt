package com.yigitkarakurt.tmdbapp.ui.detail

import android.provider.SyncStateContract.Constants
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yigitkarakurt.tmdbapp.model.MovieDetailResponse
import com.yigitkarakurt.tmdbapp.network.ApiClient
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieDetailViewModel : ViewModel() {

    val movieResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun getMovieDetail(movideId: Int) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieDetail(
                    movieId = movideId.toString(),
                    token = com.yigitkarakurt.tmdbapp.util.Constants.BEARER_TOKEN
                )

                if (response.isSuccessful) {
                    movieResponse.postValue(response.body())
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "An unknown error occured"
                    } else {
                        errorMessage.value = response.message()
                    }
                }

            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }
}