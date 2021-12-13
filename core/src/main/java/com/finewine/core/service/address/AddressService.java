package com.finewine.core.service.address;

import com.finewine.core.model.address.Address;

public interface AddressService {

    Long saveAddress(Address address, Long countryId);

    Long saveAddressForUser(Address address, Long countryId, Long userId);
}
