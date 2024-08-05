package com.lg.socialmediaapp.repository

import com.lg.socialmediaapp.ApiService
import com.lg.socialmediaapp.data.Comment
import com.lg.socialmediaapp.data.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepository(private val apiService: ApiService) {

    fun getPosts(): Flow<List<Post>> = flow {
        emit(apiService.getPosts())
    }.flowOn(Dispatchers.IO)

    fun getComments(postId: Int): Flow<List<Comment>> = flow {
        emit(apiService.getComments(postId))
    }.flowOn(Dispatchers.IO)
}