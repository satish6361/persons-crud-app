package com.assignment.requip.service;

import com.assignment.requip.dto.PersonDto;
import com.assignment.requip.entity.Person;
import com.assignment.requip.exception.DuplicateResourceException;
import com.assignment.requip.exception.ResourceNotFoundException;
import com.assignment.requip.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void shouldCreatePersonSuccessfully() {

        PersonDto dto = new PersonDto();
        dto.setName("Satish");
        dto.setEmail("satish@gmail.com");

        Person person = new Person();

        when(personRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.empty());

        when(personRepository.findByAadhaar(any()))
                .thenReturn(Optional.empty());

        when(personRepository.findByPan(any()))
                .thenReturn(Optional.empty());

        when(modelMapper.map(dto, Person.class))
                .thenReturn(person);

        when(personRepository.save(person))
                .thenReturn(person);

        when(modelMapper.map(person, PersonDto.class))
                .thenReturn(dto);

        PersonDto result = personService.createPerson(dto);

        assertEquals("Satish", result.getName());

        verify(personRepository).save(person);
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {

        PersonDto dto = new PersonDto();
        dto.setEmail("satish@gmail.com");

        when(personRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.of(new Person()));

        assertThrows(
                DuplicateResourceException.class,
                () -> personService.createPerson(dto)
        );

        verify(personRepository, never()).save(any());
    }

    @Test
    void shouldReturnPersonById() {

        Person person = new Person();
        person.setId(1L);

        PersonDto dto = new PersonDto();

        when(personRepository.findById(1L))
                .thenReturn(Optional.of(person));

        when(modelMapper.map(person, PersonDto.class))
                .thenReturn(dto);

        PersonDto result = personService.getPerson(1L);

        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenPersonNotFound() {

        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> personService.getPerson(1L)
        );
    }

    @Test
    void shouldSoftDeletePerson() {

        Person person = new Person();
        person.setId(1L);

        when(personRepository.findById(1L))
                .thenReturn(Optional.of(person));

        personService.deletePerson(1L);

        assertTrue(person.getIsDeleted());

        verify(personRepository).save(person);
    }

    @Test
    void shouldUpdatePersonSuccessfully() {

        Person existing = new Person();
        existing.setId(1L);

        PersonDto dto = new PersonDto();
        dto.setName("Updated");
        dto.setEmail("updated@gmail.com");

        when(personRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(personRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.empty());

        when(personRepository.save(existing))
                .thenReturn(existing);

        when(modelMapper.map(existing, PersonDto.class))
                .thenReturn(dto);

        PersonDto result = personService.updatePerson(1L, dto);

        assertEquals("Updated", result.getName());

        verify(personRepository).save(existing);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingWithExistingEmail() {

        Person existing = new Person();
        existing.setId(1L);

        Person duplicate = new Person();
        duplicate.setId(2L);

        PersonDto dto = new PersonDto();
        dto.setEmail("abc@gmail.com");

        when(personRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(personRepository.findByEmail(dto.getEmail()))
                .thenReturn(Optional.of(duplicate));

        assertThrows(
                DuplicateResourceException.class,
                () -> personService.updatePerson(1L, dto)
        );
    }
}