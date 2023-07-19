package br.com.amarques.customerregistration.dto.form

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class AddressForm(

    @Pattern(
        regexp = "\\d{5}-\\d{3}",
        message = "The [zipCode] must contain 8 characters respecting the following format 00000-000"
    )
    val zipCode: String,

    @field:NotEmpty(message = "The [state] cannot be empty")
    @field:Size(min = 3, max = 50, message = "The [state] must contain between [3] and [50] characters")
    val state: String,

    @field:NotEmpty(message = "The [city] cannot be empty")
    @field:Size(min = 3, max = 50, message = "The [city] must contain between [3] and [50] characters")
    val city: String,

    @field:NotEmpty(message = "The [neighborhood] cannot be empty")
    @field:Size(min = 3, max = 50, message = "The [neighborhood] must contain between [3] and [50] characters")
    val neighborhood: String,

    @field:NotEmpty(message = "The [street] cannot be empty")
    @field:Size(min = 3, max = 150, message = "The [street] must contain between [3] and [150] characters")
    val street: String,

    @field:NotEmpty(message = "The [number] cannot be empty")
    @field:Size(min = 3, max = 10, message = "The [number] must contain between [3] and [10] characters")
    val number: String,

    @field:Size(max = 200, message = "The [complement] must contain a maximum of 200 characters")
    val complement: String?
)
