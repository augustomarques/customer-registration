package br.com.amarques.customerregistration.mapper

import br.com.amarques.customerregistration.domain.Customer
import br.com.amarques.customerregistration.mapper.BankAccountMapper.Companion.mapToDomain
import br.com.amarques.customerregistration.mapper.BankAccountMapper.Companion.mapToView
import br.com.amarques.customerregistration.util.DataBankAccountTest.Companion.convertToForm
import br.com.amarques.customerregistration.util.DataBankAccountTest.Companion.generateRandomBankAccount
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BankAccountMapperTest {

    @Test
    fun `should convert BankAccountForm into BankAccount`() {
        val bankAccountForm = convertToForm(generateRandomBankAccount())
        val customer = mockk<Customer>()

        val bankAccount = mapToDomain(bankAccountForm, customer)

        assertThat(bankAccount).isNotNull

        assertThat(bankAccount.id).isNull()
        assertThat(bankAccount.bank).isEqualTo(bankAccountForm.bank)
        assertThat(bankAccount.agency).isEqualTo(bankAccountForm.agency)
        assertThat(bankAccount.account).isEqualTo(bankAccountForm.account)
        assertThat(bankAccount.customer).isEqualTo(customer)
    }

    @Test
    fun `should convert BankAccount into BankAccountView`() {
        val bankAccount = generateRandomBankAccount()

        val bankAccountView = mapToView(bankAccount)

        assertThat(bankAccountView).isNotNull

        assertThat(bankAccountView.id).isEqualTo(bankAccount.id)
        assertThat(bankAccountView.bank).isEqualTo(bankAccount.bank)
        assertThat(bankAccountView.agency).isEqualTo(bankAccount.agency)
        assertThat(bankAccountView.account).isEqualTo(bankAccount.account)
    }
}