package br.com.amarques.customerregistration.domain

import jakarta.persistence.*

@Entity
@Table(name = "bank_account")
data class BankAccount(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "bank", length = 100)
    var bank: String,

    @Column(name = "agency", length = 4)
    var agency: String,

    @Column(name = "account", length = 10)
    var account: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    val customer: Customer
)
