package br.com.amarques.customerregistration.controller

import br.com.amarques.customerregistration.dto.form.BankAccountForm
import br.com.amarques.customerregistration.dto.view.BankAccountView
import br.com.amarques.customerregistration.service.BankAccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@Tag(name = "Bank accounts")
@RestController
@RequestMapping("/customers/{customerId}/bank-accounts")
class BankAccountController(private val bankAccountService: BankAccountService) {

    var logger: Logger = LoggerFactory.getLogger(BankAccountController::class.java)

    @GetMapping
    @Operation(summary = "Search for registered bank accounts")
    fun getAll(@PathVariable customerId: Long): List<BankAccountView> {
        logger.info("REST request - Searching all bank accounts for customer [id: $customerId]")

        return bankAccountService.getAllByCustomerId(customerId)
    }

    @GetMapping("/{id}")
    @Operation(summary = "Searches for the registered bank account by id")
    fun getOne(@PathVariable customerId: Long, @PathVariable id: Long): BankAccountView {
        logger.info("REST request - searching for a bank account by customer [id: $customerId] and bankId [id: $id]")

        return bankAccountService.getById(customerId, id)
    }

    @PostMapping
    @Operation(summary = "Create a new bank account")
    fun create(
        @PathVariable customerId: Long, @RequestBody @Valid bankAccountForm: BankAccountForm,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<BankAccountView> {
        logger.info("REST request - creating a new bank account for customer [id: $customerId] - $bankAccountForm")

        val bankAccountView = bankAccountService.create(customerId, bankAccountForm)
        val uri = uriComponentsBuilder.path("/$customerId/customers/${bankAccountView.id}").build().toUri()

        return ResponseEntity.created(uri).body(bankAccountView)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates the bank account")
    fun update(
        @PathVariable customerId: Long, @PathVariable id: Long,
        @RequestBody @Valid bankAccountForm: BankAccountForm
    )
            : ResponseEntity<BankAccountView> {
        logger.info("REST request - updating the bank account [id: $id] of customer [id: $customerId] - $bankAccountForm")

        val bankAccountView = bankAccountService.update(customerId, id, bankAccountForm)

        return ResponseEntity.ok(bankAccountView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete the bank account")
    fun delete(@PathVariable customerId: Long, @PathVariable id: Long) {
        logger.info("REST request - deleting the bank account [id: $id] of customer [id: $customerId]")

        bankAccountService.delete(customerId, id)
    }
}