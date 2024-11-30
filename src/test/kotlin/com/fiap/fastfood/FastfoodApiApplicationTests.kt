package com.fiap.fastfood

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

@ContextConfiguration(classes = [FastfoodApiApplication::class])
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FastfoodApiApplicationTests {

    @Autowired
    var context: WebApplicationContext? = null

    var mvc: MockMvc? = null

}
