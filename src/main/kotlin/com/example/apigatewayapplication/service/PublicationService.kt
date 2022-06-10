package com.example.apigatewayapplication.service

import com.example.apigatewayapplication.configuration.properties.RestProperties
import com.example.apigatewayapplication.model.Publication
import com.example.apigatewayapplication.util.withLog
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder.fromUriString
import java.util.*


@Service
class PublicationService(
    private val restProperties: RestProperties,
    private val subscriptionService: SubscriptionService,
    private val restTemplate: RestTemplate = RestTemplate(),
) {

    fun getPosts(email: String): List<Publication> {
        val channels = subscriptionService.getSubscriptions(channelName = "", userEmail = email).map { it.channelName }
        val response = mutableListOf<Publication>()
        channels.forEach { channel ->
            val publicationsInChannel = getPublications(channel)

            response.addAll(publicationsInChannel)
        }

        return response.toList()
    }

    fun getPublications(channelId: String): List<Publication> = withLog("Get.Publications") {
        restTemplate.exchange(
            fromUriString(restProperties.publications.url)
                .path(restProperties.publications.getPublication)
                .encode()
                .toUriString(),
            HttpMethod.GET,
            HttpEntity.EMPTY,
            object : ParameterizedTypeReference<List<Publication>>() {},
            channelId,
        ).body ?: emptyList()
    }

    fun createPublication(publication: Publication): String = withLog("Post.Publication") {
        restTemplate.postForObject(
            fromUriString(restProperties.publications.url)
                .path(restProperties.publications.postPublication)
                .encode()
                .toUriString(),
            addIdIfEmpty(publication),
            String::class.java,
        ) ?: ""
    }

    private fun addIdIfEmpty(publication: Publication) = publication.apply {
        if (id.isEmpty()) {
            this.id = UUID.randomUUID().toString().replace("-", "")
        }
    }
}
