package com.example.apigatewayapplication.controller

import com.example.apigatewayapplication.model.Publication
import com.example.apigatewayapplication.service.PublicationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/publications")
class PublicationController(private val publicationService: PublicationService) {

    @GetMapping
    fun getPublications() = publicationService.getPublications()

    @GetMapping("/{id}")
    fun getPublication(@PathVariable id: String) = publicationService.getPublication(id)

    @PostMapping
    fun createPublication(@RequestBody publication: Publication) = publicationService.createPublication(publication)
}
