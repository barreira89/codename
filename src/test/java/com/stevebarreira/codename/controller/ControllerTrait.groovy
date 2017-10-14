package com.stevebarreira.codename.controller

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver

trait ControllerTrait {

    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver(
            exceptionMappings: ["java.lang.Exception": "error/error", "java.lang.RuntimeException": "error/error"],
            statusCodes: ["error/404": "404", "error/error": "500"]
    )
}