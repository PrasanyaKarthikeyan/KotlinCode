package com.lg.socialmediaapp.viewmode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lg.socialmediaapp.data.Comment
import com.lg.socialmediaapp.data.Post
import com.lg.socialmediaapp.repository.PostRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainViewModel(private val repository: PostRepository) : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _comments = MutableLiveData<Map<Int, List<Comment>>>()
    val comments: LiveData<Map<Int, List<Comment>>> get() = _comments

    init {
        fetchPosts()
    }
    private fun fetchPosts() {
        viewModelScope.launch {
            repository.getPosts().collect { posts ->
                _posts.value = posts
            }
        }
    }

    fun fetchComments(postId: Int) {
        Log.d("PostViewModel", "Fetching comments for postId: $postId")
        viewModelScope.launch {
            repository.getComments(postId).collect { comments ->
                _comments.value = _comments.value?.toMutableMap()?.apply {
                    put(postId, comments)
                } ?: mapOf(postId to comments)
            }
        }
    }
}