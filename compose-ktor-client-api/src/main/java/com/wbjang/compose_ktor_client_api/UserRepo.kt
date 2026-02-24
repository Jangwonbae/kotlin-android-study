package com.wbjang.compose_ktor_client_api

import io.ktor.client.request.get

object UserRepo {
    suspend fun fetchUsers(): List<User> {
        val url = "https://615075ada706cd00179b745c.mockapi.io/users"
//        return KtorClient.httpClient.get(url)
        return KtorClient.httpClient.use {
            httpClient -> httpClient.get(url)
        }
    }
}