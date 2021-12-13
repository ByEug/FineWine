package com.finewine.core.model.address;

import java.util.Optional;

public interface AddressDao {

    Optional<Address> findById(Long id);

    Long saveAddress(Address address, Long countryId);

    Long saveAddressForUser(Address address, Long countryId, Long userId);
}
