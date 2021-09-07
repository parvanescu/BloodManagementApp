package BloodManagement.ServerSide.Controller;

import BloodManagement.ServerSide.Converters.PersonConverter;
import BloodManagement.ServerSide.DataTransferObjects.PersonDTO;
import BloodManagement.ServerSide.DataTransferObjects.PersonsDTO;
import BloodManagement.ServerSide.Domain.BloodType;
import BloodManagement.ServerSide.Domain.Person;
import BloodManagement.ServerSide.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    @Autowired
    private PersonService service;

    @Autowired
    private PersonConverter converter;

    @GetMapping(value = "/persons/{id}")
    public PersonDTO handleGetPerson(@PathVariable Integer id) {
            Person person = service.getPerson(id);
            return converter.convertModelToDto(person);
    }

    @GetMapping(value ="/persons")
    public PersonsDTO handleGetAllPersons() {
        Collection<Person> persons = service.getAllPersons();
        return new PersonsDTO(
                persons.stream()
                        .map((person) -> converter.convertModelToDto(person))
                        .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/persons/filtered/{id}")
    public PersonsDTO handleGetValidDonorsForPerson(@PathVariable Integer id) {
            Collection<Person> donors = service.getValidDonorsForPerson(id);
        return new PersonsDTO(
                donors.stream()
                        .map((person) -> converter.convertModelToDto(person))
                        .collect(Collectors.toSet()));
    }

    @GetMapping(value="/persons/jpafilter/{minweight}")
    public PersonsDTO handleGetPersonsWithMinWeight(@PathVariable Double minweight){
        Collection<Person> persons = service.getPersonsWithMinWeight(minweight);
        return  new PersonsDTO(
                persons.stream()
                    .map(person -> converter.convertModelToDto(person))
                    .collect(Collectors.toSet())
        );
    }

    @PostMapping(value = "/persons")
    public PersonDTO handleAddPerson(@RequestBody PersonDTO personDto) {
            Person person = service.addPerson(
                    personDto.getId(),
                    personDto.getFullName(),
                    personDto.getWeight(),
                    personDto.getDateOfBirth(),
                    personDto.getBloodType()
            );
            return person==null? null:converter.convertModelToDto(person);
    }

    @PutMapping(value = "/persons")
    public PersonDTO handleUpdatePerson(@RequestBody PersonDTO personDto) {
        Person person = service.addPerson(
                personDto.getId(),
                personDto.getFullName(),
                personDto.getWeight(),
                personDto.getDateOfBirth(),
                personDto.getBloodType()
        );
        return converter.convertModelToDto(person);
    }

    @DeleteMapping(value = "/persons/{id}")
    public PersonDTO handleDeletePerson(@PathVariable Integer id) {
            Person person = service.deletePerson(id);
            return converter.convertModelToDto(person);
    }

    @GetMapping(value = "/persons/reports")
    public Map<BloodType, Integer> handleGetNumberOfPersonsForEachBloodType() {
        return (HashMap<BloodType,Integer>)service.getNumberOfPersonsForEachBloodType();
    }

    @GetMapping(value = "/persons/sorted")
    public PersonsDTO handleGetSortedPersons(){
        return new PersonsDTO(
                (service.getOrderedPersonsByWeight().stream()
                        .map(person -> converter.convertModelToDto(person))
                        .collect(Collectors.toSet())));
    }
}
