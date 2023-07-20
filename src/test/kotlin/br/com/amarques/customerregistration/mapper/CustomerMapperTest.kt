package br.com.amarques.customerregistration.mapper

import br.com.amarques.customerregistration.mapper.CustomerMapper.Companion.mapToDomain
import br.com.amarques.customerregistration.mapper.CustomerMapper.Companion.mapToView
import br.com.amarques.customerregistration.util.DataCustomerTest.Companion.convertToForm
import br.com.amarques.customerregistration.util.DataCustomerTest.Companion.generateRandomCustomer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CustomerMapperTest {

    @Test
    fun `should convert CustomerForm into Customer`() {
        val customerForm = convertToForm(generateRandomCustomer())

        val customer = mapToDomain(customerForm)

        assertThat(customer).isNotNull
        assertThat(customer.id).isNull()
        assertThat(customer.companyName).isEqualTo(customerForm.companyName)
        assertThat(customer.phone).isEqualTo(customerForm.phone)
        assertThat(customer.declaredRevenue).isEqualTo(customerForm.declaredRevenue)
        assertThat(customer.registrationDate).isEqualTo(LocalDate.now())

        assertThat(customer.address).isNotNull
        assertThat(customer.address.zipCode).isEqualTo(customerForm.address.zipCode)
        assertThat(customer.address.state).isEqualTo(customerForm.address.state)
        assertThat(customer.address.city).isEqualTo(customerForm.address.city)
        assertThat(customer.address.neighborhood).isEqualTo(customerForm.address.neighborhood)
        assertThat(customer.address.street).isEqualTo(customerForm.address.street)
        assertThat(customer.address.number).isEqualTo(customerForm.address.number)
        assertThat(customer.address.complement).isEqualTo(customerForm.address.complement)
    }

    @Test
    fun `should convert Customer into CustomerView`() {
        val customer = generateRandomCustomer()

        val customerView = mapToView(customer)

        assertThat(customerView).isNotNull
        assertThat(customerView.id).isEqualTo(customer.id)
        assertThat(customerView.companyName).isEqualTo(customer.companyName)
        assertThat(customerView.phone).isEqualTo(customer.phone)
        assertThat(customerView.declaredRevenue).isEqualTo(customer.declaredRevenue)
        assertThat(customerView.registrationDate).isEqualTo(customer.registrationDate)

        assertThat(customerView.address).isNotNull
        assertThat(customerView.address.zipCode).isEqualTo(customer.address.zipCode)
        assertThat(customerView.address.state).isEqualTo(customer.address.state)
        assertThat(customerView.address.city).isEqualTo(customer.address.city)
        assertThat(customerView.address.neighborhood).isEqualTo(customer.address.neighborhood)
        assertThat(customerView.address.street).isEqualTo(customer.address.street)
        assertThat(customerView.address.number).isEqualTo(customer.address.number)
        assertThat(customerView.address.complement).isEqualTo(customer.address.complement)
    }
}