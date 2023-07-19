package br.com.amarques.customerregistration.domain

import net.datafaker.Faker
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class CustomerTest {

    private lateinit var faker: Faker

    @BeforeEach
    fun setup() {
        faker = Faker()
    }

    @Test
    fun `should generate a customer`() {
        val id = faker.number().randomNumber()
        val companyName = faker.company().name()
        val phone = faker.phoneNumber().phoneNumberNational()
        val declaredRevenue = BigDecimal.valueOf(faker.number().randomNumber())
        val registrationDate = LocalDate.now()

        val zipCode = faker.address().zipCode()
        val state = faker.address().state()
        val city = faker.address().city()
        val neighborhood = faker.address().secondaryAddress()
        val street = faker.address().streetName()
        val number = faker.address().buildingNumber()
        val complement = faker.address().fullAddress()

        val customer = Customer(
            id = id,
            companyName = companyName,
            phone = phone,
            declaredRevenue = declaredRevenue,
            registrationDate = registrationDate,
            address = Address(
                zipCode = zipCode,
                state = state,
                city = city,
                neighborhood = neighborhood,
                street = street,
                number = number,
                complement = complement
            )
        )

        assertThat(customer).isNotNull
        assertThat(customer.id).isEqualTo(id)
        assertThat(customer.companyName).isEqualTo(companyName)
        assertThat(customer.phone).isEqualTo(phone)
        assertThat(customer.declaredRevenue).isEqualTo(declaredRevenue)
        assertThat(customer.registrationDate).isEqualTo(registrationDate)

        assertThat(customer.address).isNotNull
        assertThat(customer.address.zipCode).isEqualTo(zipCode)
        assertThat(customer.address.state).isEqualTo(state)
        assertThat(customer.address.city).isEqualTo(city)
        assertThat(customer.address.neighborhood).isEqualTo(neighborhood)
        assertThat(customer.address.street).isEqualTo(street)
        assertThat(customer.address.number).isEqualTo(number)
        assertThat(customer.address.complement).isEqualTo(complement)
    }
}