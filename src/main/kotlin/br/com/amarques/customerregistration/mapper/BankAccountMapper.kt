package br.com.amarques.customerregistration.mapper

import br.com.amarques.customerregistration.domain.BankAccount
import br.com.amarques.customerregistration.domain.Customer
import br.com.amarques.customerregistration.dto.form.BankAccountForm
import br.com.amarques.customerregistration.dto.view.BankAccountView

class BankAccountMapper {
    companion object {
        fun mapToDomain(bankAccountForm: BankAccountForm, customer: Customer): BankAccount {
            return BankAccount(
                bank = bankAccountForm.bank,
                agency = bankAccountForm.agency,
                account = bankAccountForm.account,
                customer = customer
            )
        }

        fun mapToView(bankAccount: BankAccount): BankAccountView {
            return BankAccountView(
                id = bankAccount.id,
                bank = bankAccount.bank,
                agency = bankAccount.agency,
                account = bankAccount.account,
            )
        }
    }

}
