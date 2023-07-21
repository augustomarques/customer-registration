package br.com.amarques.customerregistration.it

import br.com.amarques.customerregistration.util.DataCustomerTest
import br.com.amarques.customerregistration.util.DataCustomerTest.Companion.generateRandomCustomer
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

class CustomerIT : BaseIT() {

    companion object {
        const val CUSTOMER_RESOURCE = "/customers"
        const val BANK_ACCOUNT_RESOURCE = CUSTOMER_RESOURCE.plus("/%s/bank-accounts")
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `should create a new customer and then query the newly created record`() {
        val customerForm = DataCustomerTest.convertToForm(generateRandomCustomer())

        val result = mockMvc.post(CUSTOMER_RESOURCE) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(customerForm)
        }.andExpect {
            status { isCreated() }
        }.andReturn()

        val location: String = result.response.getHeader("Location").toString()

        mockMvc.get(location)
            .andExpect {
                status { is2xxSuccessful() }
                content { contentType(MediaType.APPLICATION_JSON) }

                jsonPath("$.id") { isNotEmpty() }
                jsonPath("$.companyName") { value(customerForm.companyName) }
                jsonPath("$.phone") { value(customerForm.phone) }
                jsonPath("$.declaredRevenue") { value(customerForm.declaredRevenue) }
                jsonPath("$.registrationDate") { isNotEmpty() }

                jsonPath("$.address.zipCode") { value(customerForm.address.zipCode) }
                jsonPath("$.address.state") { value(customerForm.address.state) }
                jsonPath("$.address.city") { value(customerForm.address.city) }
                jsonPath("$.address.neighborhood") { value(customerForm.address.neighborhood) }
                jsonPath("$.address.street") { value(customerForm.address.street) }
                jsonPath("$.address.number") { value(customerForm.address.number) }
                jsonPath("$.address.complement") { value(customerForm.address.complement) }
            }
    }

    @Test
    @Sql("/scripts/create_customers_and_bank_accounts.sql")
    fun `should remove customer and related bank accounts`() {
        mockMvc.get(BANK_ACCOUNT_RESOURCE.format(1))
            .andExpect {
                status { is2xxSuccessful() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.length()") { value(2) }
            }

        mockMvc.delete(CUSTOMER_RESOURCE.plus("/1"))
            .andExpect {
                status { is2xxSuccessful() }
            }

        mockMvc.get(BANK_ACCOUNT_RESOURCE.format(1))
            .andExpect {
                status { is2xxSuccessful() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.length()") { value(0) }
            }
    }
}