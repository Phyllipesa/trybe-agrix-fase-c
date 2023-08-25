package com.betrybe.agrix.solution;

import com.betrybe.agrix.farm.model.entity.Person;
import com.betrybe.agrix.farm.exception.PersonNotFoundException;
import com.betrybe.agrix.farm.model.repository.PersonRepository;
import com.betrybe.agrix.farm.util.Role;
import com.betrybe.agrix.farm.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  // (1) Configuramos o mock da camada de persistência
  @MockBean
  PersonRepository personRepository;

  // Criamos o produto que passaremos para a camada de serviço
  public Person createPerson() {
    Person person = new Person();
    person.setId(1L);
    person.setUsername("Alan more");
    person.setPassword("123456");
    person.setRole(Role.ADMIN);
    return person;
  }

  @Test
  public void testPersonCreation() {
    // (2) Criamos um novo produto para retornar
    Person person = createPerson();

    // (3) Mockamos o retorno do repository
    Mockito.when(
        personRepository.save(any(Person.class)))
        .thenReturn(person);

    // Chamamos a camada de serviço
    Person createdPerson = personService.create(person);

    // (4) Verificamos se a camada de persistência foi chamada
    Mockito.verify(personRepository).save(any(Person.class));

    // Validamos os atributos do objeto retornado
    assertEquals(1L, createdPerson.getId());
    assertEquals(person.getUsername(), createdPerson.getUsername());
    assertEquals(person.getPassword(), createdPerson.getPassword());
    assertEquals(person.getRole(), createdPerson.getRole());
  }

  @Test
  public void testPersonNotFoundExceptionInGetPersonById() {
    // (1) Mockamos o retorno do repository
    Mockito.when(personRepository.findById(any(Long.class)))
        .thenReturn(Optional.empty());

    // (2) Validamos que uma exceção é lançada
    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(eq(2L)));

    // (3) Verificamos se a camada de persistência foi chamada
    Mockito.verify(personRepository).findById(eq(2L));
  }

  @Test
  public void testPersonById() {
    // (2) Criamos um novo produto para retornar
    Person person = createPerson();

    // (3) Mockamos o retorno do repository
    Mockito.when(personRepository.findById(eq(1L)))
        .thenReturn(Optional.of(person));

    // Chamamos a camada de serviço
    Person personId = personService.getPersonById(1L);

    // (4) Verificamos se a camada de persistência foi chamada
    Mockito.verify(personRepository).findById(eq(1l));

    // Validamos os atributos do objeto retornado
    assertEquals(1L, personId.getId());
    assertEquals(person.getUsername(), personId.getUsername());
    assertEquals(person.getPassword(), personId.getPassword());
    assertEquals(person.getRole(), personId.getRole());
  }

  @Test
  public void testPersonNotFoundExceptionInGetPersonByUsername() {
    // (1) Mockamos o retorno do repository
    Mockito.when(personRepository.findByUsername(any(String.class)))
        .thenReturn(Optional.empty());

    // (2) Validamos que uma exceção é lançada
    assertThrows(PersonNotFoundException.class, () -> personService
        .getPersonByUsername("Tulio Maravilha"));

    // (3) Verificamos se a camada de persistência foi chamada
    Mockito.verify(personRepository).findByUsername("Tulio Maravilha");
  }

  @Test
  public void testGetPersonByUsername() {
    // (2) Criamos um novo produto para retornar
    Person person = createPerson();

    // (3) Mockamos o retorno do repository
    Mockito.when(personRepository.findByUsername(any(String.class)))
        .thenReturn(Optional.of(person));

    // Chamamos a camada de serviço
    Person result = personService.getPersonByUsername(person.getUsername());

    // (4) Verificamos se a camada de persistência foi chamada
    Mockito.verify(personRepository).findByUsername(person.getUsername());

    // Validamos os atributos do objeto retornado
    assertEquals(1L, result.getId());
    assertEquals(person.getUsername(), result.getUsername());
    assertEquals(person.getPassword(), result.getPassword());
    assertEquals(person.getRole(), result.getRole());
  }
}
