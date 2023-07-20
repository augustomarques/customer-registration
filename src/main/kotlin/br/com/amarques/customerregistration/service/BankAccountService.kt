package br.com.amarques.customerregistration.service

import br.com.amarques.customerregistration.domain.BankAccount
import br.com.amarques.customerregistration.dto.form.BankAccountForm
import br.com.amarques.customerregistration.dto.view.BankAccountView
import br.com.amarques.customerregistration.exception.NotFoundException
import br.com.amarques.customerregistration.mapper.BankAccountMapper.Companion.mapToDomain
import br.com.amarques.customerregistration.mapper.BankAccountMapper.Companion.mapToView
import br.com.amarques.customerregistration.repository.BankAccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BankAccountService(
    private val customerService: CustomerService,
    private val repository: BankAccountRepository
) {

    @Transactional
    fun create(customerId: Long, bankAccountForm: BankAccountForm): BankAccountView {
        val customer = customerService.getOneById(customerId)
        val bankAccount = mapToDomain(bankAccountForm, customer)

        repository.save(bankAccount)

        return mapToView(bankAccount)
    }

    fun getById(customerId: Long, id: Long): BankAccountView {
        val bankAccount = getOneByCustomerIdAndId(customerId, id)
        return mapToView(bankAccount)
    }

    fun getAllByCustomerId(customerId: Long): List<BankAccountView> {
        return repository.findByCustomerId(customerId).map { mapToView(it) }
    }

    @Transactional
    fun delete(customerId: Long, id: Long) {
        val bankAccount = getOneByCustomerIdAndId(customerId, id)
        repository.delete(bankAccount)
    }

    @Transactional
    fun update(customerId: Long, id: Long, bankAccountForm: BankAccountForm): BankAccountView {
        val bankAccount = getOneByCustomerIdAndId(customerId, id)

        bankAccount.bank = bankAccountForm.bank
        bankAccount.agency = bankAccountForm.agency
        bankAccount.account = bankAccountForm.account

        return mapToView(bankAccount)
    }

    private fun getOneByCustomerIdAndId(customerId: Long, id: Long): BankAccount {
        return repository.findByIdAndCustomerId(id, customerId)
            .orElseThrow { NotFoundException("Bank account [id: $id] not found for customer [id: $customerId]") }
    }
}