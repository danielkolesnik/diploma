package com.university.contractors.service;

import com.university.contractors.model.jpa.entity.Country;
import com.university.contractors.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private static final String LOCAL_COUNTRY_ENG_NAME = "Ukraine";

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    public Country getLocalCountry() {
        final List<Country> countryList = countryRepository.findByCountryNameEng(LOCAL_COUNTRY_ENG_NAME);

        if (countryList.size() > 1) {
            throw new RuntimeException("There more than one country with name: '" + LOCAL_COUNTRY_ENG_NAME + "'");
        }

        return countryList.stream()
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Local country with name: '" + LOCAL_COUNTRY_ENG_NAME + "' was not found in the database"));
    }
}
