package com.finewine.core.service.country;

import com.finewine.core.exception.NoElementWithSuchIdException;
import com.finewine.core.model.country.Country;
import com.finewine.core.model.country.CountryDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryDao countryDao;

    @Override
    public List<Country> getAll() {
        return countryDao.findAll();
    }

    @Override
    public Country getByCountryName(String name) {
        Optional<Country> country;
        try {
            country = countryDao.findByEnglishName(name);
        } catch (EmptyResultDataAccessException e) {
            throw new NoElementWithSuchIdException(name);
        }
        if (country.isPresent()) {
            return country.get();
        } else {
            throw new NoElementWithSuchIdException(name);
        }
    }
}
