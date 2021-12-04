package com.finewine.core.service.address;

import com.finewine.core.model.address.Address;
import com.finewine.core.model.address.AddressDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;

    @Override
    public Long saveAddress(Address address, Long countryId) {
        return addressDao.saveAddress(address, countryId);
    }
}
