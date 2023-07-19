package br.com.amarques.customerregistration.mapper

import br.com.amarques.customerregistration.domain.Address
import br.com.amarques.customerregistration.dto.form.AddressForm
import br.com.amarques.customerregistration.dto.view.AddressView

class AddressMapper {
    companion object {

        fun mapToDomain(addressForm: AddressForm): Address {
            return Address(
                zipCode = addressForm.zipCode,
                state = addressForm.state,
                city = addressForm.city,
                neighborhood = addressForm.neighborhood,
                street = addressForm.street,
                number = addressForm.number,
                complement = addressForm.complement
            )
        }

        fun mapToView(address: Address): AddressView {
            return AddressView(
                zipCode = address.zipCode,
                state = address.state,
                city = address.city,
                neighborhood = address.neighborhood,
                street = address.street,
                number = address.number,
                complement = address.complement
            )
        }
    }

}
