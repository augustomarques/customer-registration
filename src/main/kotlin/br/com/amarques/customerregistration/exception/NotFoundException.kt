package br.com.amarques.customerregistration.exception

class NotFoundException(id: Long) : RuntimeException("Record not found [id: ${id}]")