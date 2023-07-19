package br.com.amarques.customerregistration.service

import br.com.amarques.customerregistration.domain.Customer
import br.com.amarques.customerregistration.dto.form.CustomerForm
import br.com.amarques.customerregistration.dto.view.CustomerView
import br.com.amarques.customerregistration.exception.NotFoundException
import br.com.amarques.customerregistration.mapper.CustomerMapper.Companion.mapToDomain
import br.com.amarques.customerregistration.mapper.CustomerMapper.Companion.mapToView
import br.com.amarques.customerregistration.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CustomerService(private val repository: CustomerRepository) {

    @Transactional
    fun create(customerForm: CustomerForm): CustomerView {
        val customer = mapToDomain(customerForm)

        repository.save(customer)

        return mapToView(customer)
    }

    fun getById(id: Long): CustomerView {
        val customer = getOneById(id)
        return mapToView(customer)
    }

    internal fun getOneById(id: Long): Customer {
        return repository.findById(id).orElseThrow { NotFoundException(id) }
    }

    fun listAllPaginated(pageable: Pageable): Page<CustomerView> {
        return repository.findAll(pageable).map { mapToView(it) }
    }

    @Transactional
    fun delete(id: Long) {
        repository.deleteById(id)
    }

    @Transactional
    fun update(id: Long, customerForm: CustomerForm): CustomerView {
        val customer = getOneById(id)
        customer.companyName = customerForm.companyName
        customer.phone = customerForm.phone
        customer.declaredRevenue = customerForm.declaredRevenue

        val address = customer.address
        address.zipCode = customerForm.address.zipCode
        address.state = customerForm.address.state
        address.city = customerForm.address.city
        address.neighborhood = customerForm.address.neighborhood
        address.street = customerForm.address.street
        address.number = customerForm.address.number
        address.complement = customerForm.address.complement

        repository.save(customer)

        return mapToView(customer)
    }
}