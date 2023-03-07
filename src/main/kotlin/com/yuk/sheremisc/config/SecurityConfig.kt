package com.yuk.sheremisc.config

import com.yuk.sheremisc.user.JwtService
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
        http: ServerHttpSecurity,
        jwtService: JwtService
    ): SecurityWebFilterChain {
        return http {
            cors { Customizer.withDefaults<ServerHttpSecurity.CorsSpec>() }
            formLogin { disable() }
            csrf { disable() }

            oauth2Login {}
            oauth2Client { Customizer.withDefaults<OAuth2ClientSpec>() }
            oauth2ResourceServer {
                jwt {
                    jwtAuthenticationConverter = jwtService
                    jwtDecoder = jwtService
                }
            }

            authorizeExchange {
                authorize("/user/login", permitAll)
                authorize("/login", permitAll)
                authorize(anyExchange, authenticated)
            }
        }
    }
}
