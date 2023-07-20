package br.com.amarques.customerregistration.dto.view

data class BankAccountView(
    val id: Long?,
    val bank: String,
    val agency: String,
    val account: String,
)
