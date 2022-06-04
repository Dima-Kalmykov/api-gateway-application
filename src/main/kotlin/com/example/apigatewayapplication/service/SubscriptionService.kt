package com.example.apigatewayapplication.service

import com.example.apigatewayapplication.configuration.properties.RestProperties
import com.example.apigatewayapplication.model.Publication
import com.example.apigatewayapplication.model.Subscription
import com.example.apigatewayapplication.util.withLog
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder.fromUriString

@Service
class SubscriptionService(
    private val restProperties: RestProperties,
    private val restTemplate: RestTemplate = RestTemplate(),
) {

    fun getSubscription(id: String) = withLog("Get.Subscription") {
        checkNotNull(
            restTemplate.getForObject(
                buildUrl(),
                Subscription::class.java,
                id,
            )
        )
    }

    fun getSubscriptions() = withLog("Get.Subscriptions") {
        checkNotNull(
            restTemplate.exchange(
                buildUrl(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                object : ParameterizedTypeReference<List<Publication>>() {}
            ).body
        )
    }

    fun createSubscription(subscription: Subscription) = withLog("Post.Subscription") {
        checkNotNull(
            restTemplate.postForObject(
                buildUrl(),
                subscription,
                Subscription::class.java,
            )
        )
    }

    private fun buildUrl() = fromUriString(restProperties.subscription.url)
        .path(restProperties.subscription.subscriptions)
        .toUriString()
}
