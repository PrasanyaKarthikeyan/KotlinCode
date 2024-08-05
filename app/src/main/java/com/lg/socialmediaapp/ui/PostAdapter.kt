package com.lg.socialmediaapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lg.socialmediaapp.data.Comment
import com.lg.socialmediaapp.data.Post
import com.lg.socialmediaapp.databinding.ItemCommentBinding
import com.lg.socialmediaapp.databinding.ItemPostBinding
import com.lg.socialmediaapp.viewmode.MainViewModel

class PostAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var posts: List<Post> = listOf()

    fun submitList(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount() = posts.size

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var comments: List<Comment> = listOf()

    fun submitList(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]
        holder.bind(comment)
    }

    override fun getItemCount() = comments.size

    inner class CommentViewHolder(private val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.comment = comment
            binding.executePendingBindings()
        }
    }
}
