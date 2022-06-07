package com.example.apigatewayapplication.model

data class Publication(
    val channelName: String,
    val title: String,
    var id: String = "",
)
