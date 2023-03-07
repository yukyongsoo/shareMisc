package com.yuk.sheremisc.user.inbound

import com.yuk.sheremisc.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.net.URI
import java.security.Principal

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/login")
    fun login(exchange: ServerWebExchange): Mono<String> {
        return exchange.getPrincipal<Principal>()
            .switchIfEmpty {
                setRedirectUrl(URI.create("/login"), HttpStatus.TEMPORARY_REDIRECT, exchange.response)
                Mono.error(IllegalAccessException())
            }.onErrorComplete()
            .transform(userService::login)
            .doOnNext { token ->
                setTokenToCookie(token, exchange.response)
            }
            .doOnNext {
                setRedirectUrl(URI.create("/"), HttpStatus.TEMPORARY_REDIRECT, exchange.response)
            }
    }

    @GetMapping("/me")
    fun getMe(exchange: ServerWebExchange): Mono<UserResponse> {
        return exchange.getPrincipal<Principal>()
            .transform(userService::getMe)
            .transform(UserResponseConvertor::convert)
            .onErrorComplete()
    }

    private fun setTokenToCookie(
        token: String,
        response: ServerHttpResponse
    ) {
        response.addCookie(ResponseCookie.from("access_token", token).path("/").build())
    }

    private fun setRedirectUrl(
        url: URI,
        status: HttpStatus,
        response: ServerHttpResponse
    ) {
        response.statusCode = status
        response.headers.location = url
    }
}
