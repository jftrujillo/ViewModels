package com.rhodar.mobile.codescrum.viewmodel.data.model

import com.squareup.moshi.Json

data class Repo(
        val id: Long,
        val name: String,
        val description: String,
        val owner: User,
        @Json(name = "stargazers_count")
        val stars : Long,
        @Json(name = "forks_count")
        val forks : Long
)