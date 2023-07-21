package br.com.amarques.customerregistration.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(
    name = "customer",
    indexes = [Index(name = "idx_customer_sort", columnList = "registration_date DESC")]
)
@SecondaryTable(
    name = "address",
    pkJoinColumns = [PrimaryKeyJoinColumn(name = "customer_id")]
)
data class Customer(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "company_name", length = 150)
    var companyName: String,

    @Column(name = "phone", length = 14)
    var phone: String,

    @Column(name = "declared_revenue")
    var declaredRevenue: BigDecimal,

    @Column(name = "registration_date")
    val registrationDate: LocalDate,

    @Embedded
    val address: Address
)
