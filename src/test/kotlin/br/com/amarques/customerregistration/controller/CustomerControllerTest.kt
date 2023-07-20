package br.com.amarques.customerregistration.controller

import br.com.amarques.customerregistration.dto.view.AddressView
import br.com.amarques.customerregistration.dto.view.CustomerView
import br.com.amarques.customerregistration.service.CustomerService
import br.com.amarques.customerregistration.util.DataCustomerTest.Companion.convertToForm
import br.com.amarques.customerregistration.util.DataCustomerTest.Companion.convertToView
import br.com.amarques.customerregistration.util.DataCustomerTest.Companion.generateRandomCustomer
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
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@WebMvcTest(controllers = [CustomerController::class])
class CustomerControllerTest {

    companion object {
        const val RESOURCE = "/customers"
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var customerService: CustomerService

    private lateinit var faker: Faker

    @BeforeEach
    fun setup() {
        faker = Faker()
    }

    @Test
    fun `should make a request and return the registered customer with the id`() {
        val customerView = convertToView(generateRandomCustomer())
        val customerId = customerView.id

        every { customerId?.let { customerService.getById(customerId) } } returns customerView

        mockMvc.get(RESOURCE.plus("/${customerId}"))
            .andExpect {
                status { is2xxSuccessful() }
                content { contentType(MediaType.APPLICATION_JSON) }

                jsonPath("$.id") { value(customerView.id) }
                jsonPath("$.companyName") { value(customerView.companyName) }
                jsonPath("$.phone") { value(customerView.phone) }
                jsonPath("$.declaredRevenue") { value(customerView.declaredRevenue) }
                jsonPath("$.registrationDate") { value(customerView.registrationDate.toString()) }

                jsonPath("$.address.zipCode") { value(customerView.address.zipCode) }
                jsonPath("$.address.state") { value(customerView.address.state) }
                jsonPath("$.address.city") { value(customerView.address.city) }
                jsonPath("$.address.neighborhood") { value(customerView.address.neighborhood) }
                jsonPath("$.address.street") { value(customerView.address.street) }
                jsonPath("$.address.number") { value(customerView.address.number) }
                jsonPath("$.address.complement") { value(customerView.address.complement) }
            }
    }

    @Test
    fun `should make a request and create a new customer`() {
        val customer = generateRandomCustomer()
        val customerForm = convertToForm(customer)
        val customerView = convertToView(customer)

        every { customerService.create(customerForm) } returns customerView

        mockMvc.post(RESOURCE) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(customerForm)
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }

            jsonPath("$.id") { value(customerView.id) }
            jsonPath("$.companyName") { value(customerView.companyName) }
            jsonPath("$.phone") { value(customerView.phone) }
            jsonPath("$.declaredRevenue") { value(customerView.declaredRevenue) }
            jsonPath("$.registrationDate") { value(customerView.registrationDate.toString()) }

            jsonPath("$.address.zipCode") { value(customerView.address.zipCode) }
            jsonPath("$.address.state") { value(customerView.address.state) }
            jsonPath("$.address.city") { value(customerView.address.city) }
            jsonPath("$.address.neighborhood") { value(customerView.address.neighborhood) }
            jsonPath("$.address.street") { value(customerView.address.street) }
            jsonPath("$.address.number") { value(customerView.address.number) }
            jsonPath("$.address.complement") { value(customerView.address.complement) }
        }
    }

    @Test
    fun `should make a request to update the customer`() {
        val customer = generateRandomCustomer()
        val updatedCustomerForm = convertToForm(generateRandomCustomer())
        val customerView = CustomerView(
            id = customer.id,
            companyName = updatedCustomerForm.companyName,
            phone = updatedCustomerForm.phone,
            declaredRevenue = updatedCustomerForm.declaredRevenue,
            registrationDate = customer.registrationDate,
            address = AddressView(
                zipCode = updatedCustomerForm.address.zipCode,
                state = updatedCustomerForm.address.state,
                city = updatedCustomerForm.address.city,
                neighborhood = updatedCustomerForm.address.neighborhood,
                street = updatedCustomerForm.address.street,
                number = updatedCustomerForm.address.number,
                complement = updatedCustomerForm.address.complement
            )
        )

        every { customer.id?.let { customerService.update(it, updatedCustomerForm) } } returns customerView

        mockMvc.put(RESOURCE.plus("/${customer.id}")) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(updatedCustomerForm)
        }.andExpect {
            status { is2xxSuccessful() }
            content { contentType(MediaType.APPLICATION_JSON) }

            jsonPath("$.id") { value(customerView.id) }
            jsonPath("$.companyName") { value(customerView.companyName) }
            jsonPath("$.phone") { value(customerView.phone) }
            jsonPath("$.declaredRevenue") { value(customerView.declaredRevenue) }
            jsonPath("$.registrationDate") { value(customerView.registrationDate.toString()) }

            jsonPath("$.address.zipCode") { value(customerView.address.zipCode) }
            jsonPath("$.address.state") { value(customerView.address.state) }
            jsonPath("$.address.city") { value(customerView.address.city) }
            jsonPath("$.address.neighborhood") { value(customerView.address.neighborhood) }
            jsonPath("$.address.street") { value(customerView.address.street) }
            jsonPath("$.address.number") { value(customerView.address.number) }
            jsonPath("$.address.complement") { value(customerView.address.complement) }
        }
    }

    @Test
    fun `should make a request to delete the customer`() {
        val customerId = faker.number().randomNumber()

        every { customerService.delete(customerId) } just runs

        mockMvc.delete(RESOURCE.plus("/${customerId}"))
            .andExpect { status { is2xxSuccessful() } }

        verify(exactly = 1) { customerService.delete(customerId) }
    }

    @Test
    fun `should make a request and return the registered customers in pages`() {
        val pageRequest = PageRequest.of(0, 5, Sort.Direction.DESC, "registrationDate")
        val registeredCustomers = List(10) { convertToView(generateRandomCustomer()) }
        val pageImpl = PageImpl(registeredCustomers, pageRequest, registeredCustomers.size.toLong())

        every { customerService.listAllPaginated(any()) } returns pageImpl

        mockMvc.get(RESOURCE)
            .andExpect {
                status { is2xxSuccessful() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.totalElements") { value(registeredCustomers.size) }
            }
    }


}