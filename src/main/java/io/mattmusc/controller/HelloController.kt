package io.mattmusc.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MyController {
    private val logger = LoggerFactory.getLogger(MyController::class.java)

    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("message", "Hello from Kotlin and Spring MVC 5 - Hey!")
        return "index"
    }
}
