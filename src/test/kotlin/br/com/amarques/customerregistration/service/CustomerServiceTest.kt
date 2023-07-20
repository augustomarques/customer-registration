package br.com.amarques.customerregistration.service

import br.com.amarques.customerregistration.exception.NotFoundException
import br.com.amarques.customerregistration.repository.CustomerRepository
import br.com.amarques.customerregistration.util.DataCustomerTest.Companion.convertToForm
import br.com.amarques.customerregistration.util.DataCustomerTest.Companion.generateRandomCustomer
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import net.datafaker.Faker
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.util.*

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @InjectMockKs
    private lateinit var customerService: CustomerService

    private lateinit var faker: Faker

    @BeforeEach
    fun setup() {
        faker = Faker()
    }

    @Test
    fun `should create a new customer`() {
        val customerForm = convertToForm(generateRandomCustomer())

        every { customerRepository.save(any()) } answers { callOriginal() }

        val customerView = customerService.create(customerForm)

        assertThat(customerView).isNotNull
        verify(exactly = 1) { customerRepository.save(any()) }
    }

    @Test
    fun `should return the customer when searching by id`() {
        val customer = generateRandomCustomer()
        val customerId = faker.number().randomNumber()

        every { customerRepository.findById(customerId) } returns Optional.of(customer)

        val customerView = customerService.getById(customerId)

        assertThat(customerView).isNotNull
    }

    @Test
    fun `should throw exception when fetching an unregistered id`() {
        val customerId = faker.number().randomNumber()

        every { customerRepository.findById(customerId) } returns Optional.empty()

        val throwable = Assertions.catchThrowable { customerService.getById(customerId) }

        assertThat(throwable)
            .isInstanceOf(NotFoundException::class.java)
            .hasMessageContaining(customerId.toString())
    }

    @Test
    fun `should return all registered customers paginated`() {
        val pageable = mockk<Pageable>()
        val numberOfCustomers = faker.number().numberBetween(5, 20)
        val registeredCustomers = List(numberOfCustomers) { generateRandomCustomer() }

        every { customerRepository.findAll(pageable) } returns PageImpl(registeredCustomers)

        val countries = customerService.listAllPaginated(pageable)

        assertThat(countries).isNotNull.isNotEmpty
        assertThat(countries.totalElements).isEqualTo(registeredCustomers.size.toLong())
    }

    @Test
    fun `should delete`() {
        val customerId = faker.number().randomNumber()

        every { customerRepository.deleteById(any()) } answers { callOriginal() }

        customerService.delete(customerId)

        verify(exactly = 1) { customerRepository.deleteById(customerId) }
    }

    @Test
    fun `should update the customer`() {
        val customerId = faker.number().randomNumber()
        val registrationDate = LocalDate.now()
        val customer = generateRandomCustomer(customerId, registrationDate)
        val customerForm = convertToForm(generateRandomCustomer(customerId, registrationDate))

        every { customerRepository.findById(customerId) } returns Optional.of(customer)
        every { customerRepository.save(any()) } answers { callOriginal() }

        val customerView = customerService.update(customerId, customerForm)

        assertThat(customerView).isNotNull
        assertThat(customerView.id).isEqualTo(customer.id)
        assertThat(customerView.companyName).isEqualTo(customerForm.companyName)
        assertThat(customerView.phone).isEqualTo(customerForm.phone)
        assertThat(customerView.declaredRevenue).isEqualTo(customerForm.declaredRevenue)
        assertThat(customerView.registrationDate).isEqualTo(customer.registrationDate)

        assertThat(customerView.address).isNotNull
        assertThat(customerView.address.zipCode).isEqualTo(customerForm.address.zipCode)
        assertThat(customerView.address.state).isEqualTo(customerForm.address.state)
        assertThat(customerView.address.city).isEqualTo(customerForm.address.city)
        assertThat(customerView.address.neighborhood).isEqualTo(customerForm.address.neighborhood)
        assertThat(customerView.address.street).isEqualTo(customerForm.address.street)
        assertThat(customerView.address.number).isEqualTo(customerForm.address.number)
        assertThat(customerView.address.complement).isEqualTo(customerForm.address.complement)
    }
}