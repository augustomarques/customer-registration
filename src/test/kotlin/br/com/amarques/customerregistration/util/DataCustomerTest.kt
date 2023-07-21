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
            val registrationDate = LocalDate.now()

            return generateRandomCustomer(id, registrationDate)
        }

        fun generateRandomCustomer(id: Long, registrationDate: LocalDate): Customer {
            val faker = Faker()

            return Customer(
                id = id,
                companyName = faker.company().name(),
                phone = faker.phoneNumber().cellPhone(),
                declaredRevenue = BigDecimal.valueOf(faker.number().randomDouble(0, 1L, 100000000L)),
                registrationDate = registrationDate,
                address = Address(
                    zipCode = faker.address().zipCode(),
                    state = faker.address().state(),
                    city = faker.address().city(),
                    neighborhood = faker.address().secondaryAddress(),
                    street = faker.address().streetName(),
                    number = faker.address().buildingNumber(),
                    complement = faker.address().fullAddress()
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