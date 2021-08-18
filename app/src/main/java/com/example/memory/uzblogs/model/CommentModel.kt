package com.example.memory.uzblogs.model

/**
 * @author user
 * @date 18.08.2021
 */
data class CommentModel(
    val id: String,
    val message: String,
    val owner: UserModel,
    val post: String,
    val publishDate: String
)
