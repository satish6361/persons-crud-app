package com.assignment.requip.repository;

import com.assignment.requip.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    Page<Person> findByIsDeletedFalse(Pageable pageable);

    Optional<Person> findByAadhaar(String aadhaar);

    Optional<Person> findByPan(String pan);
}
