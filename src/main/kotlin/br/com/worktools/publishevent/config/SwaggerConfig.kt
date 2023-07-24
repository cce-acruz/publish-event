package br.com.worktools.publishevent.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(info = Info(title = "Conciliation API", version = "2.0", description = "Authorizer Transaction Conciliation"))
class SwaggerConfig
