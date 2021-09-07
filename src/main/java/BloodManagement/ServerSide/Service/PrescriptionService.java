package BloodManagement.ServerSide.Service;

import BloodManagement.ServerSide.CoreExceptions.InvalidIDException;
import BloodManagement.ServerSide.CoreExceptions.NullArgumentException;
import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Drug;
import BloodManagement.ServerSide.Domain.Person;
import BloodManagement.ServerSide.Domain.Prescription;
import BloodManagement.ServerSide.Repository.doctor.DoctorRepository;
import BloodManagement.ServerSide.Repository.drug.DrugRepository;
import BloodManagement.ServerSide.Repository.person.PersonRepository;
import BloodManagement.ServerSide.Repository.prescription.PrescriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {
    public static final Logger log = LoggerFactory.getLogger(PrescriptionService.class);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private DrugRepository drugRepository;




    /**
     * @param prescriptionId : An id to map the Prescription to be created. It can't be null.
     * @param doctorId : An id to get the Doctor which gave the Prescription. It can't be null.
     * @param personId : An id to get the Person which is getting the Prescription. It can't be null.
     * @param drugsIds : An list of ids to get the drugs prescripted to the Person. It can't be empty.
     * @throws ValidationException : throws this exception if the given params can't get past the validation from the repo.
     * @throws NullArgumentException : throws this exception if any of the params are null.
     * @return a Prescription instance with the given params.
     */

    public Prescription addPrescription(Integer prescriptionId, Integer doctorId, Integer personId, ArrayList<Integer> drugsIds) throws ValidationException {

        // foreach drugId in drugsIds get each drug and form an arraylist of drugs
        try{
            log.trace("addPrescription --- method entered");
            Optional<Doctor> doctor = this.doctorRepository.findById(Objects.requireNonNull(doctorId));
            Optional<Person> person = this.personRepository.findById(Objects.requireNonNull(personId));
            Prescription prescription = new Prescription();
            prescription.setId(Objects.requireNonNull(prescriptionId));
            prescription.setDoctor(Objects.requireNonNull(doctor.orElseThrow(()->{throw new InvalidIDException("The given doctorId was not valid.");})));
            prescription.setPerson(Objects.requireNonNull(person.orElseThrow(()->{throw new InvalidIDException("The given personId was not valid");})));
            prescription.setDrugs(drugsIds.stream().map(id->drugRepository.findById(Objects.requireNonNull(id)).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList()));
            prescription.setReleaseDate(LocalDate.now());
            log.trace("addPrescription --- method exited");

            return prescriptionRepository.existsById(prescriptionId)?null:prescriptionRepository.save(prescription);
        }catch (NullPointerException exception) {
            throw new NullArgumentException("Argument of addPrescription was null");
        } catch (NoSuchElementException ex2){
            throw new InvalidIDException("No entity with such id");
        }

    }

    /**
     * @param prescriptionId : An id to get the old Prescription and update its values. It can't be null.
     * @param doctorId : An id to get the Doctor which gave the Prescription. It can't be null.
     * @param personId : An id to get the Person which is getting the Prescription. It can't be null.
     * @param drugsIds : An list of ids to get the drugs prescripted to the Person. It can't be empty.
     * @throws ValidationException: throws this exception if the given params can't get past the validation from the repo.
     * @throws NullArgumentException: throws this exception if any of the params are null.
     * @return a Prescription instance with the updated values which were saved in the repository.
     */
    public Prescription updatePrescription(Integer prescriptionId, Integer doctorId, Integer personId, ArrayList<Integer> drugsIds) throws ValidationException{
        try{
            log.trace("updatePrescription --- method entered");
            Prescription prescription = this.prescriptionRepository.findById(
                    Objects.requireNonNull(prescriptionId))
                    .orElseThrow(()->{throw new InvalidIDException("The given prescriptionId does not exist in repo");});
            Optional<Doctor> doctor = this.doctorRepository.findById(Objects.requireNonNull(doctorId));
            Optional<Person> person = this.personRepository.findById(Objects.requireNonNull(personId));
            List<Drug> drugs = drugsIds.isEmpty() ? prescription.getDrugs():null;
            Prescription updatedPrescription = new Prescription();
            updatedPrescription.setId(Objects.requireNonNull(prescriptionId));
            updatedPrescription.setDoctor(Objects.requireNonNull(doctor.orElseThrow(()->{throw new InvalidIDException("The given doctorId was not valid.");})));
            updatedPrescription.setPerson(Objects.requireNonNull(person.orElseThrow(()->{throw new InvalidIDException("The given personId was not valid");})));
            updatedPrescription.setDrugs(drugs!=null?drugs:drugsIds.stream().map(id->drugRepository.findById(Objects.requireNonNull(id)).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList()));
            log.trace("updatedPrescription --- method exited");
            return prescriptionRepository.save(updatedPrescription);
        }catch (NullPointerException exception){
            throw new NullArgumentException("Argument of updatePrescription was null");
        }
    }

    /**
     * @param prescriptionId : An id which maps a certain Prescription which is to be deleted. It can't be null.
     * @throws NullArgumentException: throws this exception if any of the params are null.
     * @return a Prescription instance which was deleted from the repo.
     */

    public Prescription deletePrescription(Integer prescriptionId){
        try{
            log.trace("deletePrescription --- method entered");
            Prescription prescription = prescriptionRepository.findById(prescriptionId).orElse(null);
            if(prescription == null)
                return null;
            prescriptionRepository.deleteById(Objects.requireNonNull(prescriptionId));
            log.trace("deletePrescription --- method exited");
            return prescription;
        }catch (NullPointerException exception){
            throw new NullArgumentException("Argument of deletePrescription was null");
        }
    }

    /**
     * @param prescriptionId : An id which maps a certain Prescription . It can't be null.
     * @throws NullArgumentException: throws this exception if any of the params are null.
     * @return a Prescription instance which was mapped to the given prescriptionId.
     */
    public Prescription getPrescription(Integer prescriptionId){
        try {
            log.trace("getPrescription --- method entered");
            return prescriptionRepository.findById(prescriptionId).orElse(null);
        }catch (NullPointerException exception){
            throw new NullArgumentException("The argument of getPrescription was null");
        }finally{
            log.trace("getPrescription --- method exited");
        }
    }

    public Collection<Prescription> getAll(){
        log.trace("getAll --- method entered");
        var prescriptions = (Collection<Prescription>)prescriptionRepository.findAllWithDrugs();
        log.trace("getAll --- method exited");
        return prescriptions;
    }


    public Collection<Prescription> filterPrescriptionsByDay(LocalDate date){
        try{
            log.trace("filterPrescriptionsByDay --- method entered");
            Collection<Prescription> prescriptionsList = prescriptionRepository.findAllByReleaseDateEquals(date);
            log.trace("filterPrescriptionsByDay --- method exited");
            return prescriptionsList;
        }catch (NullPointerException exception){
            throw new NullArgumentException("The argument date was null");
        }
    }

    public Set<Prescription> getPrescriptionsWithLessDrugsThan(Integer nrOfDrugs){
        try{
            log.trace("getPrescriptionsWithLessDrugsThan --- method entered");

            Collection<Prescription> prescriptions = (Collection<Prescription>)prescriptionRepository.findAllWithDrugsCriteria();
            var filteredPrescriptions = prescriptions.stream().filter(prescription -> prescription.getDrugs()
                    .size()<Objects.requireNonNull(nrOfDrugs))
                    .collect(Collectors.toSet());
            log.trace("getPrescriptionsWithLessDrugsThan --- method exited");
            return filteredPrescriptions;

        }catch (NullPointerException exception){
            throw new NullArgumentException("The argument nrOfDrugs was null");
        }
    }

    public Set<Prescription> getPrescriptionsPrescribedInTheCurrentMonth(){
        log.trace("getPrescriptionInTheCurrentMonth --- method entered");

        Collection<Prescription> prescriptions = (Collection<Prescription>)prescriptionRepository.findByMonthWithDoctorCriteria(LocalDate.now());
        log.trace("getPrescriptionInTheCurrentMonth --- method exited");

        return prescriptions.stream().filter(prescription -> prescription.getReleaseDate().getMonth()==LocalDate.now().getMonth()).collect(Collectors.toSet());
    }
}
