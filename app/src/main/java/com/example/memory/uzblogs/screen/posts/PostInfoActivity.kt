package com.example.memory.uzblogs.screen.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.memory.uzblogs.R
import com.example.memory.uzblogs.adapter.CommentAdapter
import com.example.memory.uzblogs.api.ApiService
import com.example.memory.uzblogs.api.BaseResponse
import com.example.memory.uzblogs.model.CommentModel
import com.example.memory.uzblogs.model.PostModel
import kotlinx.android.synthetic.main.activity_post_info.*
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

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

        ApiService.apiClient().getComments(post.id).enqueue(object : Callback<BaseResponse<List<CommentModel>>>{
            override fun onResponse(
                response: Response<BaseResponse<List<CommentModel>>>?,
                retrofit: Retrofit?
            ) {
                if (response == null || response.isSuccess == false) {
                    Toast.makeText(this@PostInfoActivity, "Error Loading Comments", Toast.LENGTH_SHORT).show()
                } else {
                    recyclerView.layoutManager = LinearLayoutManager(this@PostInfoActivity)
                    recyclerView.adapter = CommentAdapter(response?.body()?.data ?: listOf())
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(this@PostInfoActivity, "Error loading Comments", Toast.LENGTH_SHORT).show()
            }
        })
    }
}