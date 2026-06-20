package com.assignment.requip.service;

import com.assignment.requip.dto.PersonDto;
import com.assignment.requip.entity.Person;
import com.assignment.requip.exception.DuplicateResourceException;
import com.assignment.requip.exception.ResourceNotFoundException;
import com.assignment.requip.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonDto createPerson(PersonDto personDto) {
        log.info("Creating person with email: {}", personDto.getEmail());

        if (personRepository.findByEmail(personDto.getEmail()).isPresent()) {
            throw new DuplicateResourceException(
                    "Person already exists with email: " + personDto.getEmail()
            );
        }

        if (personRepository.findByAadhaar(personDto.getAadhaar()).isPresent()) {
            throw new DuplicateResourceException("Aadhaar already exists");
        }

        if (personRepository.findByPan(personDto.getPan()).isPresent()) {
            throw new DuplicateResourceException("PAN already exists");
        }

        Person person = modelMapper.map(personDto, Person.class);
        Person savedPerson = personRepository.save(person);

        return modelMapper.map(savedPerson, PersonDto.class);
    }

    public Page<PersonDto> getAllPersons(int page, int size, String sortBy) {

        Pageable pageable =
                PageRequest.of(page, size, Sort.by(sortBy).ascending());

        return personRepository
                .findByIsDeletedFalse(pageable)
                .map(person -> modelMapper.map(person, PersonDto.class));
    }

    public void deletePerson(Long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Person not found"));

        person.setIsDeleted(true);

        personRepository.save(person);
    }

    public PersonDto getPerson(Long id) {

        Person person = personRepository.findById(id)
                .filter(p -> !Boolean.TRUE.equals(p.getIsDeleted()))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Person not found with id: " + id));

        return modelMapper.map(person, PersonDto.class);
    }

    public PersonDto updatePerson(Long id, PersonDto personDto) {

        Person existingPerson = personRepository.findById(id)
                .filter(p -> !Boolean.TRUE.equals(p.getIsDeleted()))
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Person not found with id: " + id));

        // Email uniqueness check
        personRepository.findByEmail(personDto.getEmail())
                .filter(person -> !person.getId().equals(id))
                .ifPresent(person -> {
                    throw new DuplicateResourceException(
                            "Email already exists: " + personDto.getEmail());
                });

        // Aadhaar uniqueness check
        if (personDto.getAadhaar() != null) {
            personRepository.findByAadhaar(personDto.getAadhaar())
                    .filter(person -> !person.getId().equals(id))
                    .ifPresent(person -> {
                        throw new DuplicateResourceException(
                                "Aadhaar already exists");
                    });
        }

        // PAN uniqueness check
        if (personDto.getPan() != null) {
            personRepository.findByPan(personDto.getPan())
                    .filter(person -> !person.getId().equals(id))
                    .ifPresent(person -> {
                        throw new DuplicateResourceException(
                                "PAN already exists");
                    });
        }

        existingPerson.setName(personDto.getName());
        existingPerson.setEmail(personDto.getEmail());
        existingPerson.setGender(personDto.getGender());
        existingPerson.setPrimaryMobile(personDto.getPrimaryMobile());
        existingPerson.setSecondaryMobile(personDto.getSecondaryMobile());
        existingPerson.setAadhaar(personDto.getAadhaar());
        existingPerson.setPan(personDto.getPan());
        existingPerson.setDateOfBirth(personDto.getDateOfBirth());
        existingPerson.setPlaceOfBirth(personDto.getPlaceOfBirth());
        existingPerson.setCurrentAddress(personDto.getCurrentAddress());
        existingPerson.setPermanentAddress(personDto.getPermanentAddress());

        Person updatedPerson = personRepository.save(existingPerson);

        return modelMapper.map(updatedPerson, PersonDto.class);
    }
}
