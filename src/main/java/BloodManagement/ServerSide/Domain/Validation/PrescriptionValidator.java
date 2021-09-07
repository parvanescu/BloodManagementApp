package BloodManagement.ServerSide.Domain.Validation;

import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Drug;
import BloodManagement.ServerSide.Domain.Person;
import BloodManagement.ServerSide.Domain.Prescription;


import java.time.LocalDate;
import java.util.List;

public class PrescriptionValidator implements Validator<Prescription> {
    @Override
    public void validate(Prescription prescription) throws ValidationException {
        StringBuilder errorMessage = new StringBuilder();

        Doctor doctor = prescription.getDoctor();
        Person person = prescription.getPerson();
        List<Drug> drugs = prescription.getDrugs();

        errorMessage.append(validateDoctorPracticeTime(doctor.getPracticingSince(),prescription.getReleaseDate()))
                    .append(validatePersonBirthdate(person.getDateOfBirth(),prescription.getReleaseDate()))
                    .append(validateDrugs(drugs));

        if (errorMessage.length()>0)throw new ValidationException(errorMessage.toString());


    }

    private String validateDoctorPracticeTime(LocalDate doctorPracticeTime, LocalDate prescriptionReleaseDate){
        return doctorPracticeTime.compareTo(prescriptionReleaseDate)>0? "The doctor must start it's practice before the prescription is released": "";
    }

    private String validatePersonBirthdate(LocalDate personBirthDate, LocalDate prescriptionReleaseDate){
        return personBirthDate.compareTo(prescriptionReleaseDate)>0? "The person must have it's birthdate before the prescription release date":"";
    }

    private String validateDrugs(List<Drug> drugs){
        return drugs.isEmpty() ? "The drugs list must not be empty":"";
    }
}
