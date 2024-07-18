package com.example.tmdbmovieapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbmovieapp.model.MovieItem
import com.example.tmdbmovieapp.network.ApiClient
import com.example.tmdbmovieapp.util.Contants
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val movieList: MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun  getMovieList() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieList(token = Contants.BEARER_TOKEN)
                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.movieItems)
                } else {
                    if (response.message().isNullOrEmpty()) {
                        errorMessage.value = "An unknown error"
                    } else {
                        errorMessage.value = response.message()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
            finally {
                isLoading.value = false
            }
        }
    }
}








