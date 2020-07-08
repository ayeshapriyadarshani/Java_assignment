package com.wiley.covid19.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.wiley.covid19.model.CaseOrigin;
import com.wiley.covid19.model.Person;
import com.wiley.covid19.model.Status;
import com.wiley.covid19.service.PersonService;

@Controller
public class PersonController {

	@Value("${msg.title}")
	private String title;

	private final int ROW_PER_PAGE = 5;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PersonService personService;

	@GetMapping(value = { "/", "/index" })
	public String index(Model model) {
		model.addAttribute("title", title);
		return "index";
	}

	@GetMapping(value = { "/person/add" })
	public String showAddPerson(Model model) {
		Person person = new Person();
		model.addAttribute("add", true);
		model.addAttribute("person", person);

		return "person-edit";
	}

	@PostMapping(value = "/person/add")
	public String addPerson(Model model, @ModelAttribute("person") Person person) {
		try {

			Person newPerson = personService.addPerson(person);

			return "redirect:/person/" + String.valueOf(newPerson.getId());

		} catch (Exception ex) {

			String errorMessage = ex.getMessage();
			logger.error(errorMessage);

			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("person", person);
			model.addAttribute("add", true);

			return "person-edit";
		}
	}

	@GetMapping(value = { "/person/{id}/edit" })
	public String showEditPerson(Model model, @PathVariable long id) {
		Person person = null;
		try {
			person = personService.findPerson(id);
		} catch (Exception ex) {
			String errorMessage = ex.getMessage();
			model.addAttribute("errorMessage", errorMessage);
		}

		model.addAttribute("add", false);
		model.addAttribute("person", person);

		return "person-edit";
	}

	@PostMapping("/person/{id}/edit")
	public String updatePerson(Model model, @PathVariable long id, @ModelAttribute("person") Person person) {

		try {
			personService.updatePersonById(id, person);
			return "redirect:/person/" + String.valueOf(person.getId());
		} catch (Exception ex) {
			String errorMessage = ex.getMessage();

			model.addAttribute("errorMessage", errorMessage);
			model.addAttribute("add", false);
			return "person-edit";
		}

	}

	@GetMapping(value = "/person/{id}")
	public String getPersonById(Model model, @PathVariable long id) {
		Person person = null;
		try {
			person = personService.findPerson(id);
		} catch (Exception ex) {
			model.addAttribute("errorMessage", ex.getMessage());
		}
		model.addAttribute("person", person);
		return "person";
	}

	@GetMapping(value = "/user/persons")
	public String getPersonList(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<Person> personList = personService.all(pageNumber, ROW_PER_PAGE);

		long count = personService.count();
		boolean hasPrev = pageNumber > 1;
		boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
		model.addAttribute("personList", personList);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNumber - 1);
		model.addAttribute("hasNext", hasNext);
		model.addAttribute("next", pageNumber + 1);
		return "person-list";
	}

	/*
	 * @GetMapping("/person/count/total") public String personCount() {
	 * logger.info("zzzzz:::::::::::" + personService.personCount()); return
	 * "index"; }
	 */

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

	@GetMapping(value = { "/person/{id}/delete" })
	public String showDeletePersonById(Model model, @PathVariable long id) {
		Person person = null;
		try {
			person = personService.findPerson(id);
		} catch (Exception ex) {
			model.addAttribute("errorMessage", "Person not found");
		}
		model.addAttribute("allowDelete", true);
		model.addAttribute("person", person);
		return "person";
	}

	@PostMapping(value = { "/person/{id}/delete" })
	public String deletePersonById(Model model, @PathVariable long id) {
		try {
			personService.deletePerson(id);
			return "redirect:/user/persons";
		} catch (Exception ex) {
			String errorMessage = ex.getMessage();
			logger.error(errorMessage);
			model.addAttribute("errorMessage", errorMessage);
			return "person";
		}
	}

	@PutMapping("/person/edit")
	public void updatePerson(@RequestBody Person personDetails) {
		personService.updatePerson(personDetails);
	}

	@GetMapping(value = "/person/summary")
	public String getPersonSummary(Model model) {

		long deadCount = personService.findPersonCountByStatusforSummary(Status.DEAD);
		long recoveredCount = personService.findPersonCountByStatusforSummary(Status.RECOVERED);
		long stillHospitalCount = personService.findPersonCountByStatusforSummary(Status.STILL_IN_HOSPITAL);

		long importedCaseCount = personService.findPersonCountByCaseOriginforSummary(CaseOrigin.IMPORTED);
		long communityCaseCount = personService.findPersonCountByCaseOriginforSummary(CaseOrigin.COMMUNITY);

		long totalCases = personService.personCount();

		model.addAttribute("dead", deadCount);
		model.addAttribute("recoveredCount", recoveredCount);
		model.addAttribute("stillHospitalCount", stillHospitalCount);
		model.addAttribute("imported", importedCaseCount);
		model.addAttribute("community", communityCaseCount);
		model.addAttribute("totalCases", totalCases);

		return "summary-list";
	}
}
