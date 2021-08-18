package com.example.memory.uzblogs.screen.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.memory.uzblogs.R
import com.example.memory.uzblogs.adapter.PostAdapter
import com.example.memory.uzblogs.api.ApiService
import com.example.memory.uzblogs.api.BaseResponse
import com.example.memory.uzblogs.model.PostModel
import com.example.memory.uzblogs.model.UserModel
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.android.synthetic.main.post_item_layout.*
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

class PostsActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var user: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        user = intent.getSerializableExtra("extra_data") as UserModel

        tvToolbarName.text = user.firstName
        swipe.setOnRefreshListener(this)
        loadPosts()
    }

    override fun onRefresh() {
        loadPosts()
    }

    fun loadPosts() {
        swipe.isRefreshing = true

        ApiService.apiClient().getPostsByUser(user.id).enqueue(object : Callback<BaseResponse<List<PostModel>>>{
            override fun onResponse(
                response: Response<BaseResponse<List<PostModel>>>?,
                retrofit: Retrofit?
            ) {
                swipe.isRefreshing = false
                post_recycler.layoutManager = LinearLayoutManager(this@PostsActivity)
                post_recycler.adapter = PostAdapter (response?.body()?.data ?: listOf())
            }

            override fun onFailure(t: Throwable?) {
                swipe.isRefreshing = false
                Toast.makeText(this@PostsActivity, "Error Fetching data", Toast.LENGTH_SHORT).show()
            }

        })
    }
}