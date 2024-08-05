package com.lg.socialmediaapp

import com.lg.socialmediaapp.data.Comment
import com.lg.socialmediaapp.data.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("comments")
    suspend fun getComments(@Query("postId") postId: Int): List<Comment>
}