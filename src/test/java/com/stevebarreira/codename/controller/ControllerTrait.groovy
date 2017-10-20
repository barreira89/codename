package com.stevebarreira.codename.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver

trait ControllerTrait {

    String defaultErrorMapping = "error/error"

    SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver(
            exceptionMappings: [
                    "java.lang.Exception"       : defaultErrorMapping,
                    "java.lang.RuntimeException": defaultErrorMapping
            ],
            statusCodes: [
                    "error/404"  : "404",
                    "error/error": "500"
            ]
    )

    ObjectMapper objectMapper = new ObjectMapper()

    def getDefaultMockMvc (Object controller){
        return MockMvcBuilders
                .standaloneSetup(controller)
                .setHandlerExceptionResolvers(exceptionResolver)
                .build()
    }
}