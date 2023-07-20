package br.com.amarques.customerregistration.controller

import br.com.amarques.customerregistration.dto.view.BankAccountView
import br.com.amarques.customerregistration.service.BankAccountService
import br.com.amarques.customerregistration.util.DataBankAccountTest.Companion.convertToForm
import br.com.amarques.customerregistration.util.DataBankAccountTest.Companion.convertToView
import br.com.amarques.customerregistration.util.DataBankAccountTest.Companion.generateRandomBankAccount
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import net.datafaker.Faker
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@WebMvcTest(controllers = [BankAccountController::class])
class BankAccountControllerTest {

    companion object {
        const val RESOURCE = "/customers/%s/bank-accounts"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var bankAccountService: BankAccountService

    private lateinit var faker: Faker

    @BeforeEach
    fun setup() {
        faker = Faker()
    }

    @Test
    fun `should make a request and return the registered bank account with the id`() {
        val customerId = faker.number().randomNumber()
        val bankAccountView = convertToView(generateRandomBankAccount())
        val bankAccountId = bankAccountView.id

        every { bankAccountId?.let { bankAccountService.getById(customerId, bankAccountId) } } returns bankAccountView

        mockMvc.get(RESOURCE.format(customerId).plus("/${bankAccountId}"))
            .andExpect {
                status { is2xxSuccessful() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id") { value(bankAccountView.id) }
                jsonPath("$.bank") { value(bankAccountView.bank) }
                jsonPath("$.agency") { value(bankAccountView.agency) }
                jsonPath("$.account") { value(bankAccountView.account) }
            }
    }

    @Test
    fun `should make a request and create a new bank account`() {
        val customerId = faker.number().randomNumber()
        val bankAccount = generateRandomBankAccount()
        val bankAccountView = convertToView(bankAccount)
        val bankAccountForm = convertToForm(bankAccount)

        every { bankAccountService.create(customerId, bankAccountForm) } returns bankAccountView

        mockMvc.post(RESOURCE.format(customerId)) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bankAccountForm)
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.id") { value(bankAccountView.id) }
            jsonPath("$.bank") { value(bankAccountForm.bank) }
            jsonPath("$.agency") { value(bankAccountForm.agency) }
            jsonPath("$.account") { value(bankAccountForm.account) }
        }
    }

    @Test
    fun `should make a request to delete the bank account`() {
        val customerId = faker.number().randomNumber()
        val bankAccountId = faker.number().randomNumber()

        every { bankAccountService.delete(customerId, bankAccountId) } just runs

        mockMvc.delete(RESOURCE.format(customerId).plus("/${bankAccountId}"))
            .andExpect { status { is2xxSuccessful() } }

        verify(exactly = 1) { bankAccountService.delete(customerId, bankAccountId) }
    }

    @Test
    fun `should make a request and return all bank accounts from customer`() {
        val customerId = faker.number().randomNumber()
        val registeredBankAccounts = List(3) { convertToView(generateRandomBankAccount()) }

        every { bankAccountService.getAllByCustomerId(customerId) } returns registeredBankAccounts

        mockMvc.get(RESOURCE.format(customerId))
            .andExpect {
                status { is2xxSuccessful() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.length()") { value(registeredBankAccounts.size) }
            }
    }

    @Test
    fun `should make a request to update the bank account`() {
        val customerId = faker.number().randomNumber()
        val bankAccountId = faker.number().randomNumber()
        val bankAccountForm = convertToForm(generateRandomBankAccount())
        val bankAccountView = BankAccountView(
            id = bankAccountId,
            bank = bankAccountForm.bank,
            agency = bankAccountForm.agency,
            account = bankAccountForm.account
        )

        every { bankAccountService.update(customerId, bankAccountId, bankAccountForm) } returns bankAccountView

        mockMvc.put(RESOURCE.format(customerId).plus("/$bankAccountId")) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bankAccountForm)
        }.andExpect {
            status { is2xxSuccessful() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.id") { value(bankAccountId) }
            jsonPath("$.bank") { value(bankAccountForm.bank) }
            jsonPath("$.agency") { value(bankAccountForm.agency) }
            jsonPath("$.account") { value(bankAccountForm.account) }
        }
    }
}