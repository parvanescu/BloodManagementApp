package BloodManagement.ServerSide.Service;

import BloodManagement.ServerSide.CoreExceptions.InvalidIDException;
import BloodManagement.ServerSide.CoreExceptions.NullArgumentException;
import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Hospital;
import BloodManagement.ServerSide.Repository.hospital.HospitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
public class HospitalService {
    public static final Logger log = LoggerFactory.getLogger(HospitalService.class);


    @Autowired
    private HospitalRepository hospitalRepository;


    /**
     * Returns a hospital
     * @param id must not be null.
     * @return a Hospital if found, otherwise null
     * @throws NullArgumentException in case one of the arguments is null
     */
    public Hospital getHospital(Integer id){
        try{
            log.trace("getHospital --- method entered.");
            Objects.requireNonNull(id);
            var hospital = this.hospitalRepository.findById(Objects.requireNonNull(id)).orElse(null);
            log.trace("getHospital --- method exited.");
            return hospital;

        }
        catch (NullPointerException nullArgumentException){
            throw new NullArgumentException("The id argument was null in getHospital.");
        }
    }

    /**
     *
     * @param id - Integer, identifier of the doctor entity
     * @param capacity - Integer, the capacity of the hospital
     * @param name - String, the name of the hospital
     * @param address - String, the address of the hospital
     * @return the added hospital if its id is not a duplicate, null otherwise
     * @throws ValidationException if the hospital object is not valid
     */
    public Hospital addHospital(Integer id, Integer capacity, String name, String address) throws ValidationException {
        try{
            log.trace("addHospital --- method entered.");

            Hospital newHospital = new Hospital();
            newHospital.setId(Objects.requireNonNull(id));
            newHospital.setCapacity(Objects.requireNonNull(capacity));
            newHospital.setName(Objects.requireNonNull(name));
            newHospital.setAddress(Objects.requireNonNull(address));
            log.trace("addHospital --- method exited.");
            return hospitalRepository.existsById(id)?null:hospitalRepository.save(newHospital);

        }catch(NullPointerException elementWasNull){
            throw new NullArgumentException("Argument of addHospital was null.");
        }
    }


    /**
     *
     * @return all the hospitals in the application
     */
    public Collection<Hospital> getAllHospitals(){
        Collection<Hospital> hospitals = hospitalRepository.getAllWithDoctors();
        return hospitals;
    }


    /**
     * @param hospitalID id of the hospital to update
     * @param newName optional containing the new name
     * @param newCapacity optional containing the new capacity
     * @param newAddress optional containing the new address
     * @return the updated Hospital
     * @throws InvalidIDException if there is no hospital with the given id
     * @throws ValidationException if there updated data is invalid
     */
    public Hospital updateHospital(Integer hospitalID, String newName, String newAddress,
                               Integer newCapacity)
            throws InvalidIDException, ValidationException {
        try{
            log.trace("updateHospital --- method entered.");

            Objects.requireNonNull(hospitalID);
            Objects.requireNonNull(newName);
            Objects.requireNonNull(newCapacity);
            Objects.requireNonNull(newAddress);

            Hospital hospital = hospitalRepository.findById(hospitalID).orElseThrow(
                    () -> new InvalidIDException("Invalid Hospital ID given."));

            Hospital updatedHospital = new Hospital();
            updatedHospital.setId(hospital.getId());
            updatedHospital.setCapacity(newCapacity);
            updatedHospital.setName(newName);
            updatedHospital.setAddress(newAddress);
            log.trace("updateHospital --- method exited.");

            return hospitalRepository.save(updatedHospital);
        }catch(NullPointerException argumentWasNull){
            throw new NullArgumentException("Argument for updateHospital was null");
        }
    }

    /**
     * @param hospitalID id of the hospital to deleted
     * @return the deleted hospital or null if there was no hospital with the given id
     * @throws NullArgumentException exception if the given id is null
     */
    public Hospital deleteHospital(Integer hospitalID){
        try{
            log.trace("deleteHospital --- method entered.");

            Objects.requireNonNull(hospitalID);
            Hospital hospital = hospitalRepository.findById(hospitalID).orElse(null);
            if(Objects.isNull(hospital))
                return null;
            hospitalRepository.delete(hospital);
            log.trace("deleteHospital --- method exited.");

            return hospital;
        }catch (NullPointerException argumentWasNull){
            throw new NullArgumentException("Argument for deleteHospital was null.");
        }
    }


    /**
     *
     * @param minCapacity the minimum capacity for a hospital to be selected
     * @return a collection containing all hospitals with capacity greater than or equal to the given value
     */
    public Collection<Hospital> getHospitalsWithMinimumCapacity(Integer minCapacity){
        try{
            log.trace("getHospitalsWithMinimumCapacity --- method entered.");

            Objects.requireNonNull(minCapacity);
            Collection<Hospital> hospitals = hospitalRepository.findAllByCapacityGreaterThanEqual(minCapacity);
            log.trace("getHospitalsWithMinimumCapacity --- method entered.");

            return hospitals;

        }catch (NullPointerException e){
            throw new NullArgumentException("Argument for filtering hospitals was null.");
        }
    }

    /**
     *
     * @return the average capacity of all the hospitals stored in the system
     */
    public Double averageHospitalCapacity(){
        log.trace("averageHospitalCapacity --- method entered.");

        Collection<Hospital> hospitals = (Collection<Hospital>) hospitalRepository.findAll();

        // max with 1 so that we do not get division by 0 when there are no hospitals in the systems
        Integer noHospitals = Math.max(hospitals.size(),1);
        Integer totalCapacity = hospitals.stream()
                                .map(Hospital::getCapacity)
                                .reduce(0, (capacitySum, hospitalCapacity) -> capacitySum += hospitalCapacity );
        log.trace("averageHospitalCapacity --- method entered.");

        return (double) (totalCapacity / noHospitals);

    }





}
