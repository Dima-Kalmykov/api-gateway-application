package com.example.apigatewayapplication.model

data class Publication(
    val channel: String,
    val postTitle: String,
    var id: String = "",
)
