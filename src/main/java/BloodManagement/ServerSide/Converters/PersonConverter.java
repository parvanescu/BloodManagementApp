package BloodManagement.ServerSide.Converters;

import BloodManagement.ServerSide.DataTransferObjects.PersonDTO;
import BloodManagement.ServerSide.Domain.BloodType;
import BloodManagement.ServerSide.Domain.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter implements Converter<Person , PersonDTO>{

    @Override
    public Person convertDtoToModel(PersonDTO dto) {
        var person = new Person();
        person.setId(dto.getId());
        person.setBloodType(BloodType.parseBloodType(dto.getBloodType()));
        person.setWeight(dto.getWeight());
        person.setFullName(dto.getFullName());
        person.setDateOfBirth(dto.getDateOfBirth());
        return person;
    }

    @Override
    public PersonDTO convertModelToDto(Person person) {
        PersonDTO dto = new PersonDTO(person.getFullName(), person.getWeight(), person.getDateOfBirth(),person.getBloodType()==null?null:person.getBloodType().toString());
        dto.setId(person.getId());
        return dto;
    }
}