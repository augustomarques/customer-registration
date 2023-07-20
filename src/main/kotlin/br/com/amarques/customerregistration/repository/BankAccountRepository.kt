package br.com.amarques.customerregistration.repository

import br.com.amarques.customerregistration.domain.BankAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BankAccountRepository : JpaRepository<BankAccount, Long> {

    fun findByIdAndCustomerId(id: Long, customerId: Long): Optional<BankAccount>

    fun findByCustomerId(customerId: Long): List<BankAccount>
}