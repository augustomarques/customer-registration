package br.com.amarques.customerregistration.dto.view

import java.math.BigDecimal
import java.time.LocalDate

data class CustomerView(
    val id: Long?,
    val companyName: String,
    val phone: String,
    val declaredRevenue: BigDecimal,
    val registrationDate: LocalDate,
    val address: AddressView
)