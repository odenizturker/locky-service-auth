package com.odenizturker.auth.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {
    @Bean
    fun userRestClient(
        @Value("\${locky.services.user.url}")
        url: String,
    ): RestClient = RestClient.builder().baseUrl(url).build()

    @Bean
    fun clientRestClient(
        @Value("\${locky.services.client.url}")
        url: String,
    ): RestClient = RestClient.builder().baseUrl(url).build()
}
