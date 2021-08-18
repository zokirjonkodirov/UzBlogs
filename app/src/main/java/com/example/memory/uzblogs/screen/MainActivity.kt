package com.example.memory.uzblogs.screen

import android.content.Intent
import android.graphics.PostProcessor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.memory.uzblogs.R
import com.example.memory.uzblogs.adapter.PostAdapter
import com.example.memory.uzblogs.adapter.PostAdapterListener
import com.example.memory.uzblogs.adapter.UserAdapterListener
import com.example.memory.uzblogs.adapter.UsersAdapter
import com.example.memory.uzblogs.api.ApiService
import com.example.memory.uzblogs.api.BaseResponse
import com.example.memory.uzblogs.model.PostModel
import com.example.memory.uzblogs.model.UserModel
import com.example.memory.uzblogs.screen.posts.PostInfoActivity
import com.example.memory.uzblogs.screen.posts.PostsActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipe.setOnRefreshListener(this)
        swipe.isRefreshing = true

        loadUsers()
        loadPosts()
    }

    fun loadUsers() {
        ApiService.apiClient().getUsers().enqueue(object  : Callback<BaseResponse<List<UserModel>>>{
            override fun onResponse(
                response: Response<BaseResponse<List<UserModel>>>?,
                retrofit: Retrofit?
            ) {
                swipe.isRefreshing = false
                user_recycler.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                user_recycler.adapter = UsersAdapter(response?.body()?.data ?: emptyList(), object : UserAdapterListener{
                    override fun onClick(item: UserModel) {
                        val intent = Intent(this@MainActivity, PostsActivity::class.java)
                        intent.putExtra("extra_data", item)
                        startActivity(intent)
                    }
                })
            }

            override fun onFailure(t: Throwable?) {
                swipe.isRefreshing = false
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun loadPosts() {
        ApiService.apiClient().getPosts().enqueue(object : Callback<BaseResponse<List<PostModel>>>{

            override fun onFailure(t: Throwable?) {
                swipe.isRefreshing = false
                Toast.makeText(this@MainActivity, "Error Fetching posts", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                response: Response<BaseResponse<List<PostModel>>>?,
                retrofit: Retrofit?
            ) {
                swipe.isRefreshing = false
                post_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                post_recycler.adapter = PostAdapter(response?.body()?.data ?: listOf(), object : PostAdapterListener{
                    override fun onClick(item: PostModel) {
                        val intent = Intent(this@MainActivity, PostInfoActivity::class.java)
                        intent.putExtra("post_info", item)
                        startActivity(intent)
                    }
                })
            }
        })
    }

    override fun onRefresh() {
        loadUsers()
        loadPosts()
    }
}