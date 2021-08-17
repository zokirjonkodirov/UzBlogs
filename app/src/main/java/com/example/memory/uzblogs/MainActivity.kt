package com.example.memory.uzblogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memory.uzblogs.adapter.PostAdapter
import com.example.memory.uzblogs.adapter.UsersAdapter
import com.example.memory.uzblogs.api.ApiService
import com.example.memory.uzblogs.api.BaseResponse
import com.example.memory.uzblogs.model.PostModel
import com.example.memory.uzblogs.model.UserModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit.Callback
import retrofit.Response
import retrofit.Retrofit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadUsers()
        loadPosts()
    }

    fun loadUsers() {
        ApiService.apiClient().getUsers().enqueue(object  : Callback<BaseResponse<List<UserModel>>>{
            override fun onResponse(
                response: Response<BaseResponse<List<UserModel>>>?,
                retrofit: Retrofit?
            ) {
                user_recycler.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                user_recycler.adapter = UsersAdapter(response?.body()?.data ?: emptyList())
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun loadPosts() {
        ApiService.apiClient().getPosts().enqueue(object : Callback<BaseResponse<List<PostModel>>>{

            override fun onFailure(t: Throwable?) {
                Toast.makeText(this@MainActivity, "Error Fetching posts", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                response: Response<BaseResponse<List<PostModel>>>?,
                retrofit: Retrofit?
            ) {
                post_recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                post_recycler.adapter = PostAdapter(response?.body()?.data ?: listOf())
            }
        })
    }
}