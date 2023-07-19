package br.com.amarques.customerregistration.dto.view

data class AddressView(
    val zipCode: String,
    val state: String,
    val city: String,
    val neighborhood: String,
    val street: String,
    val number: String,
    val complement: String?
)