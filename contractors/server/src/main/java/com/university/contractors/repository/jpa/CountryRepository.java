package com.university.contractors.repository.jpa;

import com.university.contractors.model.jpa.entity.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {

    List<Country> findByCountryNameEng(String countryNameEng);
}
