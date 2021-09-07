package BloodManagement.ServerSide.Domain.Validation;

import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Person;

import java.time.LocalDate;

/**
 * @author Marin Peptenaru
 * For more details about how validators are implemented, check the Validator interface.
 */
public class PersonValidator implements Validator<Person> {

    /**
     *
     * @param person - Person object to be validated
     * @throws ValidationException if the Person object is not valid
     */
    @Override
    public void validate(Person person) throws ValidationException {
        StringBuilder errorMessage = new StringBuilder();

        errorMessage.append( validateFullName(person.getFullName()))
                    .append( validateWeight(person.getWeight()) )
                    .append( validateDateOfBirth(person.getDateOfBirth()) );

        if(errorMessage.length() > 0) throw new ValidationException( errorMessage.toString() );
    }

    // fullName must be at least 3 letters long
    private String validateFullName(String fullName) {
        return fullName.length() >= 3? "" : "Name should be at least 3 letters long.\n";
    }

    // weight must be a non null real number
    private String validateWeight(Double weight){
        return weight > 0? "" : "Weight must be a non null real number.\n";
    }

    //dateOfBirth must be logically valid
    private String validateDateOfBirth(LocalDate dateOfBirth){
        return dateOfBirth.isBefore(LocalDate.now())? "" : "Date of birth must be logically valid.\n";
    }
}
