package BloodManagement.ServerSide.Domain.Validation;

import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.BloodType;
import BloodManagement.ServerSide.Domain.Person;
import BloodManagement.ServerSide.Domain.Transfusion;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author Marin Peptenaru
 * Validator for Transfusion objects
 * For more details on how validators are implemented, check the Validator interface.
 */
public class TransfusionValidator implements Validator<Transfusion> {

    /**
     *
     * @param transfusion - transfusion object to be validated
     * @throws ValidationException if the Transfusion object does not respect all the constraints
     */
    @Override
    public void validate(Transfusion transfusion) throws ValidationException {
        StringBuilder errorMessage = new StringBuilder();

        Person donor = transfusion.getDonor();
        Person receiver = transfusion.getReceiver();

        errorMessage.append( validatePersonIDs(donor.getId(), receiver.getId()))
                    .append( validateDonorWeight(donor.getWeight()))
                    .append( validateDonorDateOfBirth(donor.getDateOfBirth()))
                    .append( validateUnits( transfusion.getUnits()))
                    .append( validateBloodTypesCompatibility( donor.getBloodType(), receiver.getBloodType()));


        if(errorMessage.length() > 0) throw new ValidationException( errorMessage.toString() );
    }

    // Donor and receiver must be different persons
    private String validatePersonIDs(Integer donorID, Integer receiverID){
        return donorID.equals(receiverID)? "A person cannot donate blood to him//herself.\n" : "";
    }

    // Donor must have the minimum weight to donate blood
    private String validateDonorWeight(Double donorWeight){
        return donorWeight >= 45.0? "" : "A person must weight at least 45 kgs to donate blood.\n";
    }

    // Donor must be at least 18 years old
    private String validateDonorDateOfBirth(LocalDate donorDateOfBirth){
        int yearsOld = Period.between( donorDateOfBirth,LocalDate.now()).getYears();
        return yearsOld >= 18? "" : "A person must be at least 18 years old to donate blood.\n";
    }

    // Must transfuse an non null number of units of blood
    private String validateUnits(Integer units){
        return units > 0? "" : "The number of units donated must be a non null real number.\n";
    }

    // Donor and receiver must have compatible blood types
    private String validateBloodTypesCompatibility(BloodType donorBloodType, BloodType receiverBloodType){
        return donorBloodType.canDonateTo(receiverBloodType)?
                ""
                : "The donor and receier blood types are not compatible for transfusion:\n"
                + donorBloodType + " --//--> " + receiverBloodType + "\n";
    }
}
