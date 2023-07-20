package br.com.amarques.customerregistration.util

import br.com.amarques.customerregistration.domain.Address
import br.com.amarques.customerregistration.domain.Customer
import br.com.amarques.customerregistration.dto.form.AddressForm
import br.com.amarques.customerregistration.dto.form.CustomerForm
import br.com.amarques.customerregistration.dto.view.AddressView
import br.com.amarques.customerregistration.dto.view.CustomerView
import net.datafaker.Faker
import java.math.BigDecimal
import java.time.LocalDate

class DataCustomerTest {

    companion object {

        fun generateRandomCustomer(): Customer {
            val faker = Faker()

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

            return Customer(
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
        }

        fun convertToForm(customer: Customer): CustomerForm {
            return CustomerForm(
                companyName = customer.companyName,
                phone = customer.phone,
                declaredRevenue = customer.declaredRevenue,
                address = AddressForm(
                    zipCode = customer.address.zipCode,
                    state = customer.address.state,
                    city = customer.address.city,
                    neighborhood = customer.address.neighborhood,
                    street = customer.address.street,
                    number = customer.address.number,
                    complement = customer.address.complement
                )
            )
        }

        fun convertToView(customer: Customer): CustomerView {
            return CustomerView(
                id = customer.id,
                companyName = customer.companyName,
                phone = customer.phone,
                declaredRevenue = customer.declaredRevenue,
                registrationDate = customer.registrationDate,
                address = AddressView(
                    zipCode = customer.address.zipCode,
                    state = customer.address.state,
                    city = customer.address.city,
                    neighborhood = customer.address.neighborhood,
                    street = customer.address.street,
                    number = customer.address.number,
                    complement = customer.address.complement
                )
            )
        }
    }
}