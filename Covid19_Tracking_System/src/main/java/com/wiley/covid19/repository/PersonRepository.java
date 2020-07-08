package com.wiley.covid19.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.wiley.covid19.model.CaseOrigin;
import com.wiley.covid19.model.Person;
import com.wiley.covid19.model.Status;

public interface PersonRepository {

	Optional<List<Person>> findByStatus(Status status);

	Optional<List<Person>> findByProvince(String province);

	Optional<List<Person>> findByDistrict(String district);

	List<Person> findByconfirmedDate(Date date);

	Optional<List<Person>> findByCaseOrigin(CaseOrigin caseOrigin);

}