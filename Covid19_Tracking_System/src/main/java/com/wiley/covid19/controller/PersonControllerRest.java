package com.wiley.covid19.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiley.covid19.model.CaseOrigin;
import com.wiley.covid19.model.Person;
import com.wiley.covid19.model.Status;
import com.wiley.covid19.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonControllerRest {

	@Autowired
	private PersonService personService;

	@PostMapping(value = "/person/add")
	public void addPerson(Model model, @ModelAttribute("person") Person person) {
		personService.addPerson(person);
	}

	@PostMapping("/person/{id}/edit")
	public void updatePerson(@PathVariable long id, Person person) {
		personService.updatePersonById(id, person);
	}

	@GetMapping(value = "/person/{id}")
	public Person getPersonById(@PathVariable long id) {
		return personService.findPerson(id);
	}

	@GetMapping(value = "/user/persons")
	public List<Person> getPersonList() {
		return personService.totalPersonList();

	}

	@GetMapping("/person/count/total")
	public long totalPersonCount() {
		return personService.personCount();
	}

	@GetMapping("/person/count/status/{status}")
	public long personCountByStatus(@PathVariable Status status) {
		return personService.findPersonCountByStatus(status);
	}

	@GetMapping("/person/count/province/{province}")
	public long personCountByProvince(@PathVariable String province) {
		return personService.findPersonCountByProvince(province);
	}

	@GetMapping("/person/count/district/{district}")
	public long personCountByDistrict(@PathVariable String district) {
		return personService.findPersonCountByDistrict(district);
	}

	@GetMapping("/person/count/case/{case}")
	public long countByCaseOrigin(@PathVariable CaseOrigin caseOrigin) {
		return personService.findPersonCountByCaseOrigin(caseOrigin);
	}

	@DeleteMapping("/person/{id}/delete")
	public void deletePerson(@PathVariable long id) {
		personService.deletePerson(id);
	}

	@PutMapping("/person/edit")
	public void updatePerson(@RequestBody Person personDetails) {
		personService.updatePerson(personDetails);
	}

}
