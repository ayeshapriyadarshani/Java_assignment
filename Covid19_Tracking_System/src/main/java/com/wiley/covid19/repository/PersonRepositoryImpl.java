package com.wiley.covid19.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiley.covid19.model.Person;

public interface PersonRepositoryImpl extends JpaRepository<Person, Long>, PersonRepository {

}
