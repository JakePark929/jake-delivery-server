package com.jakedelivery.apigateway.config

import com.jakedelivery.apigateway.filter.ServiceApiPrivateFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RouteConfig(
    private val serviceApiPrivateFilter: ServiceApiPrivateFilter
) {
    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route {spec ->
                spec.order(-1) // 최우선 순위
                spec.path(
                    "/service-api/api/**" // 매칭할 주소
                ).filters { filterSpec ->
                    filterSpec.filter(serviceApiPrivateFilter.apply(ServiceApiPrivateFilter.Config())) // 필터지정
                    filterSpec.rewritePath("/service-api(?<segment>/?.*)", "\${segment}")
                }.uri(
                    "http://localhost:8080" // 라우팅할 주소
                )
            }
            .build()
    }
}