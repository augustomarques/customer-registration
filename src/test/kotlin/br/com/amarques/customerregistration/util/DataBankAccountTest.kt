package br.com.amarques.customerregistration.util

import br.com.amarques.customerregistration.domain.BankAccount
import br.com.amarques.customerregistration.dto.form.BankAccountForm
import br.com.amarques.customerregistration.dto.view.BankAccountView
import io.mockk.mockk
import net.datafaker.Faker

class DataBankAccountTest {

    companion object {

        fun generateRandomBankAccount(): BankAccount {
            val faker = Faker()

            val id = faker.number().randomNumber()
            val bank = faker.number().numberBetween(100, 999).toString()
            val agency = faker.number().numberBetween(1000, 9999).toString()
            val account = faker.number().numberBetween(1000000, 9999999).toString()

            return BankAccount(
                id = id,
                bank = bank,
                agency = agency,
                account = account,
                customer = mockk()
            )
        }

        fun convertToView(bankAccount: BankAccount): BankAccountView {
            return BankAccountView(
                id = bankAccount.id,
                bank = bankAccount.bank,
                agency = bankAccount.agency,
                account = bankAccount.account
            )
        }

        fun convertToForm(bankAccount: BankAccount): BankAccountForm {
            return BankAccountForm(
                bank = bankAccount.bank,
                agency = bankAccount.agency,
                account = bankAccount.account
            )
        }
    }
}