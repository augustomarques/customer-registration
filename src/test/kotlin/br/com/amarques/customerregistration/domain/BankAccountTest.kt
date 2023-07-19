package br.com.amarques.customerregistration.domain

import io.mockk.mockk
import net.datafaker.Faker
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BankAccountTest {

    private lateinit var faker: Faker

    private val customer = mockk<Customer>()

    @BeforeEach
    fun setup() {
        faker = Faker()
    }

    @Test
    fun `should generate a bank account`() {
        val id = faker.number().randomNumber()
        val bank = faker.number().numberBetween(100, 999).toString()
        val agency = faker.number().numberBetween(1000, 9990).toString()
        val account = faker.number().numberBetween(1000000, 9999999).toString()

        val bankAccount = BankAccount(
            id = id,
            bank = bank,
            agency = agency,
            account = account,
            customer = customer
        )

        assertThat(bankAccount).isNotNull

        assertThat(bankAccount.id).isEqualTo(id)
        assertThat(bankAccount.bank).isEqualTo(bank)
        assertThat(bankAccount.agency).isEqualTo(agency)
        assertThat(bankAccount.account).isEqualTo(account)
        assertThat(bankAccount.customer).isEqualTo(customer)
    }
}