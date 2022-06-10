package com.example.apigatewayapplication.service

import com.example.apigatewayapplication.configuration.properties.RestProperties
import com.example.apigatewayapplication.model.Subscription
import com.example.apigatewayapplication.model.SubscriptionWrapper
import com.example.apigatewayapplication.util.withLog
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder.fromUriString

@Service
class SubscriptionService(
    private val restProperties: RestProperties,
    private val restTemplate: RestTemplate = RestTemplate(),
) {

    fun getSubscriptions(channelName: String, userEmail: String) = withLog("Get.Subscriptions") {
        restTemplate.getForObject(
            fromUriString(restProperties.subscription.url)
                .path(restProperties.subscription.subscriptions)
                .queryParam("channelName", channelName)
                .queryParam("userEmail", userEmail)
                .encode()
                .toUriString(),
            SubscriptionWrapper::class.java,
        )?.subscriptions ?: emptyList()
    }

    fun deleteSubscription(subscription: Subscription): Any? = withLog("Delete.Subscription") {
        restTemplate.exchange(
            fromUriString(restProperties.subscription.url)
                .path(restProperties.subscription.subscriptions)
                .encode()
                .toUriString(),
            HttpMethod.DELETE,
            HttpEntity(subscription),
            Any::class.java,
        )
    }

    fun createSubscription(subscription: Subscription): Any? = withLog("Post.Subscription") {
        restTemplate.postForObject(
            fromUriString(restProperties.subscription.url)
                .path(restProperties.subscription.subscriptions)
                .encode()
                .toUriString(),
            subscription,
            Any::class.java,
        )
    }
}
