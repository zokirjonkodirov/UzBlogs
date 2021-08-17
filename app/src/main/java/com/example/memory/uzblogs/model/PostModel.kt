package com.example.memory.uzblogs.model

/**
 * @author user
 * @date 16.08.2021
 */
data class PostModel(
    val id: String,
    val owner: UserModel,
    val text: String,
    val publishDate: String,
    val image: String,
    val likes: Int,
    val tags: List<String>
)
