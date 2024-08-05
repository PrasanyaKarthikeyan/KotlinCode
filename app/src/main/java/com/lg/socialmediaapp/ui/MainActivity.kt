package com.lg.socialmediaapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lg.socialmediaapp.R
import com.lg.socialmediaapp.RetrofitClient.apiService
import com.lg.socialmediaapp.databinding.ActivityMainBinding
import com.lg.socialmediaapp.repository.PostRepository
import com.lg.socialmediaapp.viewmode.MainViewModel
import com.lg.socialmediaapp.viewmode.MainViewModelFectory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Set up the Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Posts"

        val repository = PostRepository(apiService)
        val factory = MainViewModelFectory(repository)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        postAdapter = PostAdapter(viewModel)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }

        viewModel.posts.observe(this, Observer {
            postAdapter.submitList(it)
        })

        viewModel.comments.observe(this, Observer { commentsMap ->
            commentsMap.forEach { (postId, comments) ->
                val viewHolder = binding.recyclerView.findViewHolderForAdapterPosition(postId - 1)
                val commentRecyclerView = viewHolder?.itemView?.findViewById<RecyclerView>(R.id.commentRecyclerView)
                if (commentRecyclerView != null) {
                    commentRecyclerView.visibility = View.VISIBLE
                    val commentAdapter = CommentAdapter()
                    commentRecyclerView.adapter = commentAdapter
                    commentAdapter.submitList(comments)
                }
            }
        })
    }
}