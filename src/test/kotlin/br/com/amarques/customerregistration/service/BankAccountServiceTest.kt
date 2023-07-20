package br.com.amarques.customerregistration.service

import br.com.amarques.customerregistration.exception.NotFoundException
import br.com.amarques.customerregistration.repository.BankAccountRepository
import br.com.amarques.customerregistration.util.DataBankAccountTest.Companion.convertToForm
import br.com.amarques.customerregistration.util.DataBankAccountTest.Companion.generateRandomBankAccount
import br.com.amarques.customerregistration.util.DataCustomerTest.Companion.generateRandomCustomer
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import net.datafaker.Faker
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class BankAccountServiceTest {

    @MockK
    private lateinit var bankAccountRepository: BankAccountRepository

    @MockK
    private lateinit var customerService: CustomerService

    @InjectMockKs
    private lateinit var bankAccountService: BankAccountService

    private lateinit var faker: Faker

    @BeforeEach
    fun setup() {
        faker = Faker()
    }

    @Test
    fun `should create a new bank account`() {
        val customerId = faker.number().randomNumber()
        val customer = generateRandomCustomer(customerId, LocalDate.now())
        val bankAccountForm = convertToForm(generateRandomBankAccount())

        every { bankAccountRepository.save(any()) } answers { callOriginal() }
        every { customerService.getOneById(customerId) } returns customer

        val bankAccountView = bankAccountService.create(customerId, bankAccountForm)

        assertThat(bankAccountView).isNotNull()
        verify(exactly = 1) { bankAccountRepository.save(any()) }
    }

    @Test
    fun `should return the bank account when searching by id`() {
        val customerId = faker.number().randomNumber()
        val bankAccountId = faker.number().randomNumber()
        val bankAccount = generateRandomBankAccount()

        every {
            bankAccountRepository.findByIdAndCustomerId(
                bankAccountId, customerId
            )
        } returns Optional.of(bankAccount)

        val bankAccountView = bankAccountService.getById(customerId, bankAccountId)

        assertThat(bankAccountView).isNotNull
    }

    @Test
    fun `should throw exception when fetching an unregistered id`() {
        val customerId = faker.number().randomNumber()
        val bankAccountId = faker.number().randomNumber()

        every { bankAccountRepository.findByIdAndCustomerId(bankAccountId, customerId) } returns Optional.empty()

        val throwable = Assertions.catchThrowable { bankAccountService.getById(customerId, bankAccountId) }

        assertThat(throwable)
            .isInstanceOf(NotFoundException::class.java)
            .hasMessageContaining(customerId.toString())
            .hasMessageContaining(bankAccountId.toString())
    }

    @Test
    fun `should return all customers bank accounts`() {
        val customerId = faker.number().randomNumber()
        val registeredBankAccounts = List(3) { generateRandomBankAccount() }

        every { bankAccountRepository.findByCustomerId(customerId) } returns registeredBankAccounts

        val bankAccounts = bankAccountService.getAllByCustomerId(customerId)

        assertThat(bankAccounts).isNotNull.isNotEmpty
        assertThat(bankAccounts).size().isEqualTo(registeredBankAccounts.size)
    }

    @Test
    fun `should delete the bank account`() {
        val customerId = faker.number().randomNumber()
        val bankAccountId = faker.number().randomNumber()
        val bankAccount = generateRandomBankAccount()

        every {
            bankAccountRepository.findByIdAndCustomerId(
                bankAccountId, customerId
            )
        } returns Optional.of(bankAccount)
        every { bankAccountRepository.delete(bankAccount) } answers { callOriginal() }

        bankAccountService.delete(customerId, bankAccountId)

        verify(exactly = 1) { bankAccountRepository.delete(bankAccount) }
    }

    @Test
    fun `should update the bank account`() {
        val customerId = faker.number().randomNumber()
        val bankAccountId = faker.number().randomNumber()
        val bankAccount = generateRandomBankAccount(bankAccountId)
        val bankAccountForm = convertToForm(generateRandomBankAccount(bankAccountId))

        every {
            bankAccountRepository.findByIdAndCustomerId(
                bankAccountId, customerId
            )
        } returns Optional.of(bankAccount)

        val bankAccountView = bankAccountService.update(customerId, bankAccountId, bankAccountForm)

        assertThat(bankAccountView.id).isEqualTo(bankAccount.id)
        assertThat(bankAccountView.bank).isEqualTo(bankAccountForm.bank)
        assertThat(bankAccountView.agency).isEqualTo(bankAccountForm.agency)
        assertThat(bankAccountView.account).isEqualTo(bankAccountForm.account)
    }
}