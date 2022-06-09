package com.example.apigatewayapplication.service

import com.example.apigatewayapplication.configuration.properties.RestProperties
import com.example.apigatewayapplication.model.SubscriptionWrapper
import com.example.apigatewayapplication.util.withLog
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
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

    fun deleteSubscription(subscription: String, channelName: String): Any? = withLog("Delete.Subscription") {
        makeRequest(subscription, channelName)
    }

    fun createSubscription(subscription: String, channelName: String): Any? = withLog("Post.Subscription") {
        makeRequest(subscription, channelName)
    }

    private fun makeRequest(subscription: String, channelName: String): Any? {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val requestEntity = HttpEntity(subscription, headers)

        return restTemplate.postForObject(
            fromUriString(restProperties.subscription.url)
                .path(restProperties.subscription.subscriptions)
                .path("/{channelName}")
                .encode()
                .toUriString(),
            requestEntity,
            Any::class.java,
            channelName,
        )
    }
}
