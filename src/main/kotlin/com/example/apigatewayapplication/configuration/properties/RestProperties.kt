package com.example.apigatewayapplication.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty

@ConfigurationProperties(prefix = "rest")
data class RestProperties(
    @NestedConfigurationProperty val publications: PublicationProperties = PublicationProperties(),
    @NestedConfigurationProperty val subscription: SubscriptionProperties = SubscriptionProperties(),
)

data class PublicationProperties(
    val publication: String = "/publications",
) {
    lateinit var url: String
}

data class SubscriptionProperties(
    val subscriptions: String = "/subscriptions"
) {
    lateinit var url: String
}