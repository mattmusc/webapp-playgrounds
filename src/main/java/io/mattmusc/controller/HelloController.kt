package io.mattmusc.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.view.RedirectView
import java.net.http.HttpResponse

@Controller
class MyController {
    private val logger = LoggerFactory.getLogger(MyController::class.java)

    @GetMapping("/")
    fun home(): RedirectView {
        return RedirectView("swagger-ui.html")
    }
}
