package com.fiap.fastfood

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FastfoodApiApplication

fun main(args: Array<String>) {
	runApplication<FastfoodApiApplication>(*args)
}
