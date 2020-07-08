package com.wiley.covid19.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wiley.covid19.exception.ApiRequestException;
import com.wiley.covid19.model.CaseOrigin;
import com.wiley.covid19.model.Person;
import com.wiley.covid19.model.Status;
import com.wiley.covid19.repository.PersonRepositoryImpl;

@Service
public class PersonService {

	@Autowired
	PersonRepositoryImpl personRepository;

	private boolean existsById(Long id) {
		return personRepository.existsById(id);
	}

	public Person addPerson(Person person) {

		if (!StringUtils.isEmpty(person.getName())) {
			if (existsById(person.getId())) {
				throw new ApiRequestException("Person with id: " + person.getId() + " already exists");
			}
			return personRepository.save(person);

		} else {
			ApiRequestException exc = new ApiRequestException("Failed to save person");
			throw exc;
		}
	}

	public Person findPerson(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new ApiRequestException("Person Id not found " + id));
	}

	public void deletePerson(Long id) {

		if (!existsById(id)) {
			throw new ApiRequestException("Cannot find person with id: " + id);
		} else {
			personRepository.deleteById(id);
		}
	}

	public void updatePerson(Person personDetails) {

		personRepository.findById(personDetails.getId())
				.orElseThrow(() -> new ApiRequestException("Cannot find person with id: " + personDetails.getId()));

		personRepository.save(personDetails);
	}

	public void updatePersonById(long id, Person persondetails) {
		Person person = personRepository.findById(id)
				.orElseThrow(() -> new ApiRequestException("Cannot find person with id: " + id));
		person.setId(id);
		person.setName(persondetails.getName());
		person.setDistrict(persondetails.getDistrict());
		person.setContactNumber(persondetails.getContactNumber());
		person.setProvince(persondetails.getProvince());
		person.setStatus(persondetails.getStatus());
		person.setCaseOrigin(persondetails.getCaseOrigin());
		personRepository.save(person);
	}

	public List<Person> totalPersonList() {
		return personRepository.findAll();
	}

	public List<Person> all(int pageNumber, int rowPerPage) {
		List<Person> personList = new ArrayList<>();
		Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage, Sort.by("id").ascending());
		personRepository.findAll(sortedByIdAsc).forEach(personList::add);
		return personList;
	}

	public long personCount() {
		return personRepository.findAll().stream().count();
	}

	public long findPersonCountByStatus(Status status) {

		Optional<List<Person>> personList = personRepository.findByStatus(status);

		if (personList.isPresent()) {
			return personList.get().size();
		} else {
			throw new ApiRequestException("Status not found :" + status);
		}
	}

	public long findPersonCountByStatusforSummary(Status status) {

		Optional<List<Person>> personList = personRepository.findByStatus(status);

		if (personList.isPresent()) {
			return personList.get().size();
		} else {
			return 0;
		}
	}

	public int findPersonCountByProvince(String province) {

		Optional<List<Person>> personList = personRepository.findByProvince(province);

		if (personList.isPresent()) {
			return personList.get().size();
		} else {
			throw new ApiRequestException("Province not found :" + province);
		}
	}

	public long findPersonCountByDistrict(String district) {

		Optional<List<Person>> personList = personRepository.findByDistrict(district);

		if (personList.isPresent()) {
			return personList.get().size();
		} else {
			throw new ApiRequestException("District not found :" + district);
		}
	}

	public long findPersonCountByCaseOrigin(CaseOrigin caseOrigin) {

		Optional<List<Person>> personList = personRepository.findByCaseOrigin(caseOrigin);

		if (personList.isPresent()) {
			return personList.get().size();
		} else {
			throw new ApiRequestException("Case Origin not found :" + caseOrigin);
		}
	}

	public long findPersonCountByCaseOriginforSummary(CaseOrigin caseOrigin) {

		Optional<List<Person>> personList = personRepository.findByCaseOrigin(caseOrigin);

		if (personList.isPresent()) {
			return personList.get().size();
		} else {
			return 0;
		}
	}

	public Long count() {
		return personRepository.count();
	}

	public long findPersonCountByDate(Date date) {

		Optional<List<Person>> personList = Optional.ofNullable(personRepository.findByconfirmedDate(date));

		if (personList.isPresent()) {
			return personList.get().size();
		} else {
			throw new ApiRequestException("Date not found :" + date);
		}
	}

	public long findPersonCountByDateforSummary(Date date) {

		Optional<List<Person>> personList = Optional.ofNullable(personRepository.findByconfirmedDate(date));

		if (personList.isPresent()) {
			return personList.get().size();
		} else {
			return 0;
		}
	}

}
