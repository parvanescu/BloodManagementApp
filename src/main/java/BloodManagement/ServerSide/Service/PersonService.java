package BloodManagement.ServerSide.Service;

import BloodManagement.ServerSide.CoreExceptions.InvalidIDException;
import BloodManagement.ServerSide.CoreExceptions.NullArgumentException;
import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.BloodType;
import BloodManagement.ServerSide.Domain.Person;
import BloodManagement.ServerSide.Repository.person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonService {
    public static final Logger log = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    /**
     * Returns a person after an ID
     *
     * @param id must not be null.
     * @return a Person if found, otherwise it returns null
     * @throws NullArgumentException in case one of the arguments is null
     */
    public Person getPerson(Integer id) throws NullArgumentException {
        try {
            log.trace("getPerson --- method entered");
            return this.personRepository.findById(Objects.requireNonNull(id)).orElse(null);
        } catch (NullPointerException nullArgumentException) {
            throw new NullArgumentException("An argument was null in getPerson.");
        } finally {
            log.trace("getPerson --- method exited");
        }
    }


    /**
     * @return all donors
     */
    public Collection<Person> getAllPersons() {
        return (this.personRepository.findAll());
    }


    public Collection<Person> getValidDonorsForPerson(Integer ID) throws InvalidIDException, NullArgumentException {
        BloodType targetBloodType;
        try {
            log.trace("getValidDonorsForPerson --- method entered");

            targetBloodType = this.personRepository.findById(Objects.requireNonNull(ID))
                    .orElseThrow(() -> new InvalidIDException("No person with such ID")).getBloodType();

            log.trace("getValidDonorsForPerson --- method exited");

        } catch (NullPointerException nullArgumentException) {
            throw new NullArgumentException("An argument was null in getPerson.");
        }
        return ((Collection<Person>) this.personRepository.findAll()).stream().filter(person -> person.getBloodType().canDonateTo(targetBloodType) && !person.getId().equals(ID)).collect(Collectors.toList());
    }


    /**
     * Saves a person
     *
     * @param name        must not be null.
     * @param weight      must not be null.
     * @param dateOfBirth must not be null.
     * @param bloodType   must not be null.
     * @return the person entity if the entity was created, otherwise returns null if it already existed.
     * @throws NullArgumentException in case one of the arguments is null
     */
    public Person addPerson(Integer id, String name, Double weight, LocalDate dateOfBirth, String bloodType) throws ValidationException {
        try {
            log.trace("addPerson --- method entered");
            Person newPerson = new Person(
                    Objects.requireNonNull(id)
                    , Objects.requireNonNull(name)
                    , Objects.requireNonNull(weight)
                    , Objects.requireNonNull(dateOfBirth)
                    , BloodType.parseBloodType(bloodType)
            );
            log.trace("addPerson --- method exited");

            // the BloodManagement.core.Repository interface returns an empty optional if the new entity was correctly added
            // so the function returns the new person if it was correctly added to the repository.
            return personRepository.existsById(id)?null:this.personRepository.save(newPerson);
        } catch (NullPointerException elementWasNull) {
            throw new NullArgumentException("Argument of addReceiver was null");
        }
    }

    /**
     * @param personID  id of the person to update
     * @param newName   optional containing the new name
     * @param newWeight optional containing the new weight
     * @return the updated Person
     * @throws InvalidIDException  if there is no person with the given id
     * @throws ValidationException if there updated data is invalid
     */
    public Person updatePerson(Integer personID, String newName, Double newWeight,
                               String newBloodTypeString, LocalDate newDateOfBirth)
            throws InvalidIDException, ValidationException {
        try {
            log.trace("updatePerson --- method entered");

            Objects.requireNonNull(personID);
            Objects.requireNonNull(newName);
            Objects.requireNonNull(newWeight);
            BloodType newBloodType = BloodType.parseBloodType(newBloodTypeString);

            Person person = personRepository.findById(personID).orElseThrow(
                    () -> new InvalidIDException("Invalid Person ID given."));

            Person updatedPerson = new Person(person.getId(), newName,
                    newWeight, newDateOfBirth,
                    newBloodType);
            log.trace("updatePerson --- method exited");

            return personRepository.save(updatedPerson);
        } catch (NullPointerException argumentWasNull) {
            throw new NullArgumentException("Argument for update Person was null");
        }
    }

    /**
     * @param personID id of the person to deleted
     * @return the deleted person or null if there was no person with the given id
     * @throws NullArgumentException exception if the given id is null
     */
    public Person deletePerson(Integer personID) {
        try {
            log.trace("deletePerson --- method entered");
            Objects.requireNonNull(personID);
            Person deletedPerson = personRepository.findById(personID).orElse(null);
            if (deletedPerson == null)
                return null;
            personRepository.deleteById(personID);
            log.trace("deletePerson --- method exited");

            return deletedPerson;
        } catch (NullPointerException argumentWasNull) {
            throw new NullArgumentException("Argument for deletePerson was null.");
        }
    }

    /**
     * @return a map that maps each blood type to the number of persons registered in the system that have that blood type
     */
    public Map<BloodType, Integer> getNumberOfPersonsForEachBloodType() {
        log.trace("getNumberOfPersonsForEachBloodType --- method entered");

        Collection<BloodType> bloodTypes = BloodType.getBloodTypes();
        Map<BloodType, Integer> personsPerBloodType = new HashMap<>();

        bloodTypes.forEach(bloodType -> personsPerBloodType.putIfAbsent(bloodType, 0));

        Collection<Person> persons = (Collection<Person>) personRepository.findAll();
        persons.forEach(person -> personsPerBloodType.computeIfPresent(person.getBloodType(),
                (blood, noPersons) -> noPersons + 1));
        log.trace("getNumberOfPersonsForEachBloodType --- method exited");

        return personsPerBloodType;
    }

    public Collection<Person> getOrderedPersonsByWeight(){
        log.trace("getOrderedPersonByWeight --- method entered");
        log.trace("getOrderedPersonByWeight --- method exited");
        return personRepository.findByOrderByWeightAsc();
    }

    public Collection<Person> getPersonsWithMinWeight(Double minWeight){
        log.trace("getPersonsWithMinWeight --- method entered");
        log.trace("getPersonsWithMinWeight--- method exited");
        return personRepository.getAllByWeightGreaterThan(minWeight);
    }

}
