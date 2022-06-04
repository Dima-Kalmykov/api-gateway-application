package com.example.apigatewayapplication.controller

import com.example.apigatewayapplication.model.Subscription
import com.example.apigatewayapplication.service.SubscriptionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/subscription")
class SubscriptionController(private val subscriptionService: SubscriptionService) {

    @GetMapping
    fun getSubscriptions() = subscriptionService.getSubscriptions()

    @GetMapping("/{id}")
    fun getSubscription(@PathVariable id: String) = subscriptionService.getSubscription(id)

    @PostMapping
    fun createSubscription(@RequestBody subscription: Subscription) = subscriptionService.createSubscription(subscription)
}
