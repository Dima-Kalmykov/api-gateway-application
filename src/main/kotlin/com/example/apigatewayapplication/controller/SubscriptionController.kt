package com.example.apigatewayapplication.controller

import com.example.apigatewayapplication.model.Subscription
import com.example.apigatewayapplication.service.SubscriptionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/subscription")
class SubscriptionController(private val subscriptionService: SubscriptionService) {

    @GetMapping
    fun getSubscriptions(
        @RequestParam(defaultValue = "") channelName: String,
        @RequestParam(defaultValue = "") userEmail: String,
    ) = subscriptionService.getSubscriptions(channelName, userEmail)

    @PostMapping("/{channelName}")
    fun createSubscription(
        @PathVariable channelName: String,
        @RequestBody userEmail: String,
    ) = subscriptionService.createSubscription(userEmail, channelName)

    @DeleteMapping("/{channelName}")
    fun deleteSubscription(
        @PathVariable channelName: String,
        @RequestBody userEmail: String,
    ) = subscriptionService.deleteSubscription(userEmail, channelName)
}
