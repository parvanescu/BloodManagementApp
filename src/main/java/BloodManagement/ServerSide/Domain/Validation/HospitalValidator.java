package BloodManagement.ServerSide.Domain.Validation;

import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Hospital;


/**
 @author Marin Peptenaru
  * */
public class HospitalValidator implements Validator<Hospital> {
    @Override
    public void validate(Hospital hospital) throws ValidationException {
        StringBuilder errorMessage = new StringBuilder();

        errorMessage.append( validateName(hospital.getName()))
                .append( validateAddress(hospital.getAddress()))
                .append(validateCapacity(hospital.getCapacity()))
        ;

        if(errorMessage.length() > 0) throw new ValidationException( errorMessage.toString() );
    }

    private String validateName(String name){
        return name.length() >= 3? "" : "Name must be at least 3 letters long.\n";
    }

    private String validateAddress(String address){
        return address.length() >= 3? "" : "Address must be at least 3 letters long.\n";

    }

    private String validateCapacity(Integer capacity){
        return capacity >= 0? "" : "Capacity must be a non null integer number.\n";
    }
}
