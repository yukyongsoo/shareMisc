package com.yuk.sheremisc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ClientSpec
import org.springframework.security.config.web.server.invoke
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun getSecurityWebFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        return http {
            cors { }
            formLogin { disable() }
            csrf { disable() }

            oauth2Login {}
            oauth2Client { Customizer.withDefaults<OAuth2ClientSpec>() }
            oauth2ResourceServer { jwt {} }

            authorizeExchange {
                authorize("/user/login", permitAll)
                authorize("/login", permitAll)
                authorize(anyExchange, authenticated)
            }
        }
    }
}
