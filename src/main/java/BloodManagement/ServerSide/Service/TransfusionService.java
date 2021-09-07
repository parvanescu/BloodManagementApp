package BloodManagement.ServerSide.Service;

import BloodManagement.ServerSide.CoreExceptions.InvalidIDException;
import BloodManagement.ServerSide.CoreExceptions.NullArgumentException;
import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Person;
import BloodManagement.ServerSide.Domain.Transfusion;
import BloodManagement.ServerSide.Repository.doctor.DoctorRepository;
import BloodManagement.ServerSide.Repository.person.PersonRepository;
import BloodManagement.ServerSide.Repository.transfusion.TransfusionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
// attendance lab
@Service
public class TransfusionService {
    public static final Logger log = LoggerFactory.getLogger(TransfusionService.class);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TransfusionRepository transfusionRepository;
    @Autowired
    private DoctorRepository doctorRepository;


    /**
     * Returns all the transfusions
     * @return a List
     */
    public Collection<Transfusion> getAllTransfusions(){
        return this.transfusionRepository.getAllWithEveryAttributeCriteria();
    }
    /**
     * Returns a transfusion
     * @param ID must not be null.
     * @return a Transfusion if found, otherwise null
     * @throws NullArgumentException in case one of the arguments is null
     */
    public Transfusion getTransfusion(Integer ID) throws NullArgumentException {
        try{
            log.trace("getTransfusion --- method entered");
            var transfusion = this.transfusionRepository.findById(Objects.requireNonNull(ID)).orElse(null);
            log.trace("getTransfusion --- method exited");
            return transfusion;

        }
        catch (NullPointerException nullArgumentException){
            throw new NullArgumentException("An argument was null in getTransfusion.");
        }
    }
    /**
     * Saves a transfusion
     * @param donorId must not be null.
     * @param receiverId must not be null.
     * @param units must not be null.
     * @return the new transfusion if the entity was created, otherwise returns null if it already existed.
     * @throws NullArgumentException in case one of the arguments is null
     */
    public Transfusion addTransfusion(Integer id, Integer donorId, Integer receiverId, Integer units, Integer doctorId) throws ValidationException, NullArgumentException, InvalidIDException {
        try{
            log.trace("addTransfusion --- method entered");

            Objects.requireNonNull(donorId);
            Objects.requireNonNull(receiverId);
            Doctor doctor = doctorRepository.findById(Objects.requireNonNull(doctorId))
                    .orElseThrow(()->new InvalidIDException("Doctor with given ID does not exist"));
            Person donor = personRepository.findById(donorId)
                    .orElseThrow(()->new InvalidIDException("Donor with given ID does not exist"));
            Person receiver = personRepository.findById(receiverId)
                    .orElseThrow(()->new InvalidIDException("Receiver with given ID does not exist"));
            Transfusion newTransfusion = new Transfusion(
                    Objects.requireNonNull(id),
                    Objects.requireNonNull(donor)
                    ,Objects.requireNonNull(receiver)
                    , doctor
                    , Objects.requireNonNull(units)
            );
            // the BloodManagement.core.Repository interface returns an empty optional if the new entity was correctly added
            // so the function returns the new transfusion if it was correctly added to the repository.
            log.trace("getTransfusion --- method exited");
            return transfusionRepository.existsById(id)?null:this.transfusionRepository.save(newTransfusion);

        } catch(NullPointerException elementWasNull){
            throw new NullArgumentException("Argument of addTransfusion was null");
        }
    }

    /**
     * @return all donors
     */
    public Set<Person> getAllDonors (){
        log.trace("getAllDonors --- method entered");

        var donors = ((Collection<Transfusion>) this.transfusionRepository.findAllWithDonor()).stream().map(Transfusion::getDonor).collect(Collectors.toSet());
        log.trace("getAllDonors--- method exited");

        return donors;
    }


    /**
     * @return a all receivers
     */
    public Set<Person> getAllReceivers(){
        log.trace("getAllReceivers --- method entered");

        var receivers = ((Collection<Transfusion>) this.transfusionRepository.findAllWithReceiver()).stream().map(Transfusion::getReceiver).collect(Collectors.toSet());
        log.trace("getAllReceivers --- method exited");

        return receivers;
    }






    /**
     * @param transfusionID of the transfusion to delete
     * @return the deleted transfusion or null if there was no person with the given id
     * @throws NullArgumentException exception if the given id is null
     */
    public Transfusion deleteTransfusion(Integer transfusionID){
        try{
            log.trace("deleteTransfusion --- method entered");

            Objects.requireNonNull(transfusionID);
            Transfusion transfusionToBeDeleted = transfusionRepository.findById(transfusionID).orElse(null);
            if(transfusionToBeDeleted == null)
                return null;
            transfusionRepository.deleteById(transfusionID);
            log.trace("deleteTransfusion --- method exited");

            return transfusionToBeDeleted;
        }catch (NullPointerException argumentWasNull){
            throw new NullArgumentException("Argument for deleteTransfusion was null.");
        }
    }


    /**
     *
     * @param transfusionID id of the doctor to be updated
     * @param newDonor optional containing the id of the new donor
     * @param newReceiver optional containing the id of the new receiver
     * @param newDoctor optional containing the id of the new doctor
     * @param newUnits optional containing the new number of units of blood donated
     * @return the update doctor
     * @throws InvalidIDException if an invalid id is given for any of the entities
     * @throws ValidationException if the new data is invalid
     */
    public Transfusion updateTransfusion(Integer transfusionID, Integer newDonor, Integer newReceiver,
                               Integer newDoctor, Integer newUnits )
            throws InvalidIDException, ValidationException {
        try{
            log.trace("updateTransfusion --- method entered");

            Objects.requireNonNull(transfusionID);
            Objects.requireNonNull(newDonor);
            Objects.requireNonNull(newReceiver);
            Objects.requireNonNull(newDoctor);
            Objects.requireNonNull(newUnits);

            Transfusion transfusion = transfusionRepository.findById(transfusionID).orElseThrow(
                    () -> new InvalidIDException("Invalid transfusion ID received as parameter")
            );


            Transfusion updatedTransfusion = new Transfusion(transfusionID, transfusion.getDonor(),
                    transfusion.getReceiver(), transfusion.getDoctor(), newUnits);


            Person updateDonor = personRepository.findById(newDonor).orElseThrow(
                    () -> new InvalidIDException("Invalid Donor ID received as parameter"));
            updatedTransfusion.setDonor(updateDonor);


            Person updateReceiver = personRepository.findById(newReceiver).orElseThrow(
                    () -> new InvalidIDException("Invalid Receiver ID received as parameter"));
            updatedTransfusion.setReceiver(updateReceiver);


            Doctor updateDoctor = doctorRepository.findById(newDoctor).orElseThrow(
                        () -> new InvalidIDException("Invalid Doctor ID received as parameter"));
            updatedTransfusion.setDoctor(updateDoctor);


            log.trace("updateTransfusion --- method exited");

            return transfusionRepository.save(updatedTransfusion);


        }catch (NullPointerException argumentWasNull){
            throw new NullArgumentException("Argument for updateDoctor was null.");
        }
    }


    /**
     * @param doctorID - the id of the doctor by which we filter transfusions
     * @return a collection containing all transfusions performed by a given doctor
     */
    public Collection<Transfusion> getTransfusionsPerformedByDoctor(Integer doctorID){
            try{
                log.trace("getTransfusionPerformedByDoctor --- method entered");

                Objects.requireNonNull(doctorID);
                Doctor doc = doctorRepository.getOne(doctorID);
                Collection<Transfusion> transfusions = transfusionRepository.findAllByDoctorEquals(doc);
                log.trace("getTransfusionPerformedByDoctor --- method exited");
                return transfusions;

            }catch (NullPointerException e){
                throw new NullArgumentException("Given doctorId was null.");
            }

    }

    public Map<Doctor,Collection<Person>> getDoctorsWithListOfDonorsHandled() {
        log.trace("getDoctorsWithListOfDonorsHandled --- method entered");
        Collection<Transfusion> transfusions = transfusionRepository.getAllWithDonorAndDoctorCriteria();
        Map<Doctor,Collection<Person>> map = new HashMap<>();
        transfusions.forEach(transfusion -> {
            Collection<Person> donors = map.computeIfAbsent(transfusion.getDoctor(),k -> new HashSet<>(Collections.singletonList(transfusion.getDonor())));
            if(donors!=null)
                donors.add(transfusion.getDonor());

        });
        log.trace("getDoctorsWithListOfDonorsHandled --- method exited");
        return map;
    }

}
