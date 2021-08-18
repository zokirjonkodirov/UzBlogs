package com.example.memory.uzblogs.screen.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.memory.uzblogs.R
import com.example.memory.uzblogs.model.PostModel
import kotlinx.android.synthetic.main.activity_post_info.*

class PostInfoActivity : AppCompatActivity() {

    lateinit var post: PostModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_info)

        loadPostInfo()
        btnBack.setOnClickListener {
            finish()
        }
    }

    fun loadPostInfo() {
        post = intent.getSerializableExtra("post_info") as PostModel

        Glide.with(this).load(post.image).into(infoImage)
        tvPostName.text = post.text
        tvLike.text = post.likes.toString()
    }
}