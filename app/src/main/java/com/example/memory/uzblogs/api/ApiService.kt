package com.example.memory.uzblogs.api

import retrofit.GsonConverterFactory
import retrofit.Retrofit

/**
 * @author user
 * @date 17.08.2021
 */
object ApiService {
    var retrofit: Retrofit? = null

    fun apiClient(): Api {
        if (retrofit == null) {
            retrofit = Retrofit
                .Builder()
                .baseUrl("https://dummyapi.io/data/v1/")
                .addConverterFactory(GsonConverterFactory.create()  )
                .build()
        }

        return retrofit!!.create(Api::class.java)
    }
}