package br.com.amarques.customerregistration.domain

import jakarta.persistence.*

@Entity
@Table(name = "bank_account")
data class BankAccount(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "bank")
    var bank: Int,

    @Column(name = "agency")
    var agency: Int,

    @Column(name = "account", length = 10)
    var account: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    val customer: Customer
)
