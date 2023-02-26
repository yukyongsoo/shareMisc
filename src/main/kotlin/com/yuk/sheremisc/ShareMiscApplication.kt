package com.yuk.sheremisc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.tools.agent.ReactorDebugAgent

@SpringBootApplication
class ShareMiscApplication

fun main(args: Array<String>) {
    ReactorDebugAgent.init()
    runApplication<ShareMiscApplication>(*args)
}
