package com.example.apigatewayapplication.util

import mu.KotlinLogging

private val logger = KotlinLogging.logger { }

fun <T> withLog(message: String, function: () -> T): T {
    logger.info { "$message.Request" }
    val result = function()
    logger.info { "$message.Response" }

    return result
}
