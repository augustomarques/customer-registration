package br.com.amarques.customerregistration.repository

import br.com.amarques.customerregistration.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long>