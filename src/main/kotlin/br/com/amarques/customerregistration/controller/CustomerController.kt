package br.com.amarques.customerregistration.controller

import br.com.amarques.customerregistration.dto.form.CustomerForm
import br.com.amarques.customerregistration.dto.view.CustomerView
import br.com.amarques.customerregistration.service.CustomerService
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort.Direction.DESC
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    var logger: Logger = LoggerFactory.getLogger(CustomerController::class.java)

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 5, sort = ["registrationDate"], direction = DESC) pageable: Pageable)
            : Page<CustomerView> {
        logger.info("REST request - Searching all customers by page")

        return customerService.listAllPaginated(pageable)
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): CustomerView {
        logger.info("REST request - searching for a customer by ID [id: $id]")

        return customerService.getById(id)
    }

    @PostMapping
    fun create(@RequestBody @Valid customerForm: CustomerForm, uriComponentsBuilder: UriComponentsBuilder)
            : ResponseEntity<CustomerView> {
        logger.info("REST request - creating a new customer $customerForm")

        val customerView = customerService.create(customerForm)
        val uri = uriComponentsBuilder.path("/customers/${customerView.id}").build().toUri()

        return ResponseEntity.created(uri).body(customerView)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid customerForm: CustomerForm)
            : ResponseEntity<CustomerView> {
        logger.info("REST request - updating the customer [id: $id] $customerForm")

        val customerView = customerService.update(id, customerForm)

        return ResponseEntity.ok(customerView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        logger.info("REST request - deleting the customer [id: $id]")

        customerService.delete(id)
    }
}