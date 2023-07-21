package br.com.amarques.customerregistration.controller

import br.com.amarques.customerregistration.dto.form.CustomerForm
import br.com.amarques.customerregistration.dto.view.CustomerView
import br.com.amarques.customerregistration.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@Tag(name = "Customers")
@RestController
@RequestMapping("/customers")
class CustomerController(private val customerService: CustomerService) {

    var logger: Logger = LoggerFactory.getLogger(CustomerController::class.java)

    @GetMapping
    @Operation(summary = "Search for registered customers")
    fun getAll(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "5") size: Int,
        @RequestParam(defaultValue = "registrationDate") sortList: Array<String>,
        @RequestParam(defaultValue = "DESC") sortOrder: String,
    ): Page<CustomerView> {
        logger.info("REST request - Searching all customers by page")

        val sortDirection = Sort.Direction.fromString(sortOrder)
        val orderList = sortList.map { Sort.Order(sortDirection, it) }

        val pageRequest = PageRequest.of(page, size, Sort.by(orderList))

        return customerService.listAllPaginated(pageRequest)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Searches for the registered customer by id")
    fun getOne(@PathVariable id: Long): CustomerView {
        logger.info("REST request - searching for a customer by ID [id: $id]")

        return customerService.getById(id)
    }

    @PostMapping
    @Operation(summary = "Register a new customer")
    fun create(@RequestBody @Valid customerForm: CustomerForm, uriComponentsBuilder: UriComponentsBuilder)
            : ResponseEntity<CustomerView> {
        logger.info("REST request - creating a new customer $customerForm")

        val customerView = customerService.create(customerForm)
        val uri = uriComponentsBuilder.path("/customers/${customerView.id}").build().toUri()

        return ResponseEntity.created(uri).body(customerView)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the customer")
    fun update(@PathVariable id: Long, @RequestBody @Valid customerForm: CustomerForm)
            : ResponseEntity<CustomerView> {
        logger.info("REST request - updating the customer [id: $id] $customerForm")

        val customerView = customerService.update(id, customerForm)

        return ResponseEntity.ok(customerView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete the customer")
    fun delete(@PathVariable id: Long) {
        logger.info("REST request - deleting the customer [id: $id]")

        customerService.delete(id)
    }
}