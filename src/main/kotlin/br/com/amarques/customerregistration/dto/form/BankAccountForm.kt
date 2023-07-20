package br.com.amarques.customerregistration.dto.form

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class BankAccountForm(

    @field:NotEmpty(message = "The [bank] cannot be empty")
    @field:Size(min = 3, max = 3, message = "The [bank] must contain a maximum of [3] characters")
    val bank: String,

    @field:NotEmpty(message = "The [companyName] cannot be empty")
    @field:Size(min = 4, max = 4, message = "The [agency] must contain [4] characters")
    val agency: String,

    @field:NotEmpty(message = "The [account] cannot be empty")
    @field:Size(max = 10, message = "The [account] must contain a maximum of 10 characters")
    val account: String
)
