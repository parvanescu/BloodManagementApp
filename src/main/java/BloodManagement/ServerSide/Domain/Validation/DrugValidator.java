package BloodManagement.ServerSide.Domain.Validation;

import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Drug;

public class DrugValidator implements Validator<Drug>{
    @Override
    public void validate(Drug entity) throws ValidationException {
        StringBuilder errorMessage = new StringBuilder();

        errorMessage.append(validateName(entity.getName()))
                    .append(validateRecommendedAmount(entity.getRecommendedAmount()));

        if (errorMessage.length() > 0) throw new ValidationException(errorMessage.toString());
    }

    private String validateRecommendedAmount(Integer recommendedAmount) {
        return recommendedAmount > 0? "" : "The daily recommended amount for a certain drug as to be at least 1 per day.\n";
    }

    private String validateName(String name){
        return name.length() > 0? "" : "The name has to have at least one character.";
    }
}
