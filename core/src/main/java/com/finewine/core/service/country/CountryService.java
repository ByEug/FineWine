package com.finewine.core.service.country;

import com.finewine.core.model.country.Country;

import java.util.List;

public interface CountryService {

    List<Country> getAll();

    Country getByCountryName(String name);
}
