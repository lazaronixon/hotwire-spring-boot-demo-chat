package com.example.hotwirespringbootdemochat.controllers

import org.springframework.web.servlet.mvc.support.RedirectAttributes

enum class FlashType(val value: String) {
    NOTICE("notice"), ALERT("alert")
}

abstract class ApplicationController {

    protected fun redirectTo(url: String): String {
        return "redirect:${url}"
    }

    protected fun redirectTo(url: String, flash: RedirectAttributes, flashType: FlashType, flashMessage: String) : String {
        flash.addFlashAttribute(flashType.value, flashMessage); return redirectTo(url)
    }

}