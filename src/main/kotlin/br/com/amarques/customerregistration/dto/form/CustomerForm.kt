package br.com.amarques.customerregistration.dto.form

import jakarta.validation.constraints.*
import java.math.BigDecimal

data class CustomerForm(

    @field:NotEmpty(message = "The [companyName] cannot be empty")
    @field:Size(min = 3, max = 150, message = "The [companyName] must contain between [3] and [150] characters")
    val companyName: String,

    @field:NotEmpty(message = "The [phone] cannot be empty")
    @Pattern(
        regexp = "(\\d{2})\\d{5}-\\d{4}$",
        message = "The [phone] number must contain 11 numbers respecting the following format (00)00000-0000"
    )
    val phone: String,

    @field:NotNull(message = "The [declaredRevenue] is mandatory")
    @field:DecimalMin(value = "0.0", inclusive = false)
    val declaredRevenue: BigDecimal,

    @field:NotNull(message = "The [address] is required")
    val address: AddressForm
)