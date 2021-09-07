package BloodManagement.ServerSide.Domain.Validation;

import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Doctor;

import java.time.LocalDate;

/**
@author Marin Peptenaru
* */
public class DoctorValidator implements Validator<Doctor> {
    @Override
    public void validate(Doctor doctor) throws ValidationException {
        StringBuilder errorMessage = new StringBuilder();

        errorMessage.append( validateFullName(doctor.getFullName()))
                    .append( validatePracticingSince(doctor.getPracticingSince()) );

        if(errorMessage.length() > 0) throw new ValidationException( errorMessage.toString() );
    }

    private String validateFullName(String fullName){
        return fullName.length() >= 3? "" : "Name must be at least 3 letters long.\n";
    }

    private String validatePracticingSince(LocalDate startedPracticing){
        return startedPracticing.isBefore(LocalDate.now())? "" :
                "The date in which the doctor has started practicing must be logically valid.\n";
    }
}
