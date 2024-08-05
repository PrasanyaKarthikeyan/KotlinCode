package com.lg.socialmediaapp.viewmode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lg.socialmediaapp.repository.PostRepository

class MainViewModelFectory (private val repository: PostRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}