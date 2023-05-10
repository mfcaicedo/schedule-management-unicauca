package com.pragma.api.repository;

import com.pragma.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, String> {
}
