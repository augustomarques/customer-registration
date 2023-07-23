package br.com.amarques.customerregistration.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(servers = [Server(url = "/", description = "Default Server URL")])
class OpenApiConfiguration