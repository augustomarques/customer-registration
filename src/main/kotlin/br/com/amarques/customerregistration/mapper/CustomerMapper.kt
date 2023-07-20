package br.com.amarques.customerregistration.mapper

import br.com.amarques.customerregistration.domain.Customer
import br.com.amarques.customerregistration.dto.form.CustomerForm
import br.com.amarques.customerregistration.dto.view.CustomerView
import br.com.amarques.customerregistration.mapper.AddressMapper.Companion.mapToDomain
import br.com.amarques.customerregistration.mapper.AddressMapper.Companion.mapToView
import java.time.LocalDate

class CustomerMapper {

    companion object {
        fun mapToDomain(customerForm: CustomerForm): Customer {
            return Customer(
                companyName = customerForm.companyName,
                phone = customerForm.phone,
                declaredRevenue = customerForm.declaredRevenue,
                registrationDate = LocalDate.now(),
                address = mapToDomain(customerForm.address)
            )
        }

        fun mapToView(customer: Customer): CustomerView {
            return CustomerView(
                id = customer.id,
                companyName = customer.companyName,
                phone = customer.phone,
                declaredRevenue = customer.declaredRevenue,
                registrationDate = customer.registrationDate,
                address = mapToView(customer.address)
            )
        }
    }
}
