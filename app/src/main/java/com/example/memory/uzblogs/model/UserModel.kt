package com.example.memory.uzblogs.model

import java.io.Serializable

/**
 * @author user
 * @date 16.08.2021
 */
data class UserModel(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val picture: String
): Serializable