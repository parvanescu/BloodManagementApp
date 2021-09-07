package BloodManagement.ServerSide.Domain.Validation;

import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.BaseEntity;

/** @author Marin Peptenaru
 *
 * @param <T> - the type of the entities that are to be validated
 */
public interface Validator<T extends BaseEntity<?>> {

    /**
     * The purpose of the validate method is to call other methods,
     * each responsible for verifying one integrity constraint.
     * Each of those methods will return an error message if the constraint that it enforces is not respected.
     * Those error messages will be grouped into a single error message which will be used as the message
     * for an exception thrown by the validate method.
     * @param entity - the entity to be validated
     * @throws ValidationException if the entity violates any integrity constraint
     */
    void validate(T entity) throws ValidationException;
}
