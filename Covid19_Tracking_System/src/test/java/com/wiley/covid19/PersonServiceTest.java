package com.wiley.covid19;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wiley.covid19.model.Address;
import com.wiley.covid19.model.CaseOrigin;
import com.wiley.covid19.model.Person;
import com.wiley.covid19.model.Status;
import com.wiley.covid19.service.PersonService;

public class PersonServiceTest {

	@Mock
	private PersonService personService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSavedUserSuccessfully() {

		final Address address = new Address();
		address.setCity("city");
		address.setHouseNumber(23);
		address.setStreet("street 01");

		final Person person = new Person("Ayesha", address, "011 -2345", "matara", "south", new Date(), true,
				Status.RECOVERED, CaseOrigin.COMMUNITY);

		personService.addPerson(person);
		verify(personService, times(1)).addPerson(person);

	}

	@Test
	public void shouldUpdateSuccessfully() {

		final Address address = new Address();
		address.setCity("city");
		address.setHouseNumber(23);
		address.setStreet("street 01");

		final Person person = new Person("Ayesha", address, "011 -2345", "matara", "south", new Date(), true,
				Status.RECOVERED, CaseOrigin.COMMUNITY);

		personService.updatePerson(person);
		verify(personService, times(1)).updatePerson(person);

	}

	@Test
	public void getPersonByIdTest() {
		final Address address = new Address();
		address.setCity("city");
		address.setHouseNumber(23);
		address.setStreet("street 01");

		when(personService.findPerson(1L)).thenReturn(new Person("Ayesha", address, "011 -2345", "matara", "south",
				new Date(), true, Status.RECOVERED, CaseOrigin.COMMUNITY));

		Person person = personService.findPerson(1L);

		assertEquals("Ayesha", person.getName());
		assertEquals(CaseOrigin.COMMUNITY, person.getCaseOrigin());
		assertEquals("011 -2345", person.getContactNumber());
	}

	@Test
	public void getAllPersonsTest() {
		final Address address1 = new Address();
		address1.setCity("city");
		address1.setHouseNumber(23);
		address1.setStreet("street 01");

		final Address address2 = new Address();
		address2.setCity("city");
		address2.setHouseNumber(23);
		address2.setStreet("street 01");

		final Address address3 = new Address();
		address3.setCity("city");
		address3.setHouseNumber(23);
		address3.setStreet("street 01");

		List<Person> list = new ArrayList<Person>();
		Person pOne = new Person("Ayesha", address1, "011 -2345", "matara", "south", new Date(), true, Status.RECOVERED,
				CaseOrigin.COMMUNITY);
		Person pTwo = new Person("Ayesha", address2, "011 -2345", "matara", "south", new Date(), true, Status.RECOVERED,
				CaseOrigin.COMMUNITY);
		Person pThree = new Person("Ayesha", address3, "011 -2345", "matara", "south", new Date(), true,
				Status.RECOVERED, CaseOrigin.COMMUNITY);

		list.add(pOne);
		list.add(pTwo);
		list.add(pThree);

		when(personService.all(1, 1)).thenReturn(list);

		// test
		List<Person> pList = personService.all(1, 1);

		assertEquals(3, pList.size());
		verify(personService, times(1)).all(1, 1);
	}

	@Test
	public void getPersonCountByStatusTest() {

		when(personService.findPersonCountByStatus(Status.RECOVERED)).thenReturn(2L);

		long count = personService.findPersonCountByStatus(Status.RECOVERED);

		assertEquals(2, count);
		verify(personService, times(1)).findPersonCountByStatus(Status.RECOVERED);

	}

	@Test
	public void getPersonCountByProvinceTest() {

		when(personService.findPersonCountByProvince("Sabaragamuva")).thenReturn(1);

		long count = personService.findPersonCountByProvince("Sabaragamuva");

		assertEquals(1, count);
		verify(personService, times(1)).findPersonCountByProvince("Sabaragamuva");

	}

	@Test
	public void getPersonCountByDistrictTest() {

		when(personService.findPersonCountByDistrict("Matara")).thenReturn(2L);

		long count = personService.findPersonCountByDistrict("Matara");

		assertEquals(2, count);
		verify(personService, times(1)).findPersonCountByDistrict("Matara");

	}

	@Test
	public void shouldDeleteSuccessfully() {

		final Address address = new Address();
		address.setCity("city");
		address.setHouseNumber(23);
		address.setStreet("street 01");

		final Person person = new Person("Ayesha", address, "011 -2345", "matara", "south", new Date(), true,
				Status.RECOVERED, CaseOrigin.COMMUNITY);
		person.setId(1L);

		personService.deletePerson(1L);
		verify(personService, times(1)).deletePerson(1L);

	}

}
