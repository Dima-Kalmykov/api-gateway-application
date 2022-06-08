package com.example.apigatewayapplication.model

data class Subscription(
    val channel: Channel,
    val user: User,
)

data class Channel(val name: String)

data class User(val email: String)
