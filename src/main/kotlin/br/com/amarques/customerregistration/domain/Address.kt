package br.com.amarques.customerregistration.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Address(

    @Column(name = "zip_code", length = 9, table = "address")
    var zipCode: String,

    @Column(name = "state", length = 50, table = "address")
    var state: String,

    @Column(name = "city", length = 50, table = "address")
    var city: String,

    @Column(name = "neighborhood", length = 50, table = "address")
    var neighborhood: String,

    @Column(name = "street", length = 150, table = "address")
    var street: String,

    @Column(name = "number", length = 10, table = "address")
    var number: String,

    @Column(name = "complement", length = 200, table = "address")
    var complement: String?
)
