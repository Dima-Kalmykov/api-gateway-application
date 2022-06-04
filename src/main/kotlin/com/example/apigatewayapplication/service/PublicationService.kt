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


@Service
class PublicationService(
    private val restProperties: RestProperties,
    private val restTemplate: RestTemplate = RestTemplate(),
) {

    fun getPublication(id: String) = withLog("Get.Publication") {
        checkNotNull(
            restTemplate.getForObject(
                buildUrl(),
                Publication::class.java,
                id,
            )
        )
    }

    fun getPublications() = withLog("Get.Publications") {
        checkNotNull(
            restTemplate.exchange(
                buildUrl(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                object : ParameterizedTypeReference<List<Publication>>() {}
            ).body
        )
    }

    fun createPublication(publication: Publication) = withLog("Post.Publication") {
        checkNotNull(
            restTemplate.postForObject(
                buildUrl(),
                publication,
                Publication::class.java,
            )
        )
    }

    private fun buildUrl() = fromUriString(restProperties.publications.url)
        .path(restProperties.publications.publication)
        .toUriString()
}
