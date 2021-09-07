package BloodManagement.ServerSide.Service;

import BloodManagement.ServerSide.CoreExceptions.InvalidIDException;
import BloodManagement.ServerSide.CoreExceptions.NullArgumentException;
import BloodManagement.ServerSide.CoreExceptions.ValidationException;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Hospital;
import BloodManagement.ServerSide.Repository.doctor.DoctorRepository;
import BloodManagement.ServerSide.Repository.hospital.HospitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

@Service
public class DoctorService {
    public static final Logger log = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private HospitalRepository hospitalRepository;


    /**
     *
     * @param id - Integer, identifier of the doctor entity
     * @param name - String
     * @param practicingSince - date since when the doctor has been practicing
     * @return the added doctor if its id is not a duplicate, null otherwise
     * @throws ValidationException if the doctor object is not valid
     */
    public Doctor addDoctor(Integer id, String name, LocalDate practicingSince, Integer hospitalID) throws ValidationException, InvalidIDException {
        try{
            log.trace("addDoctor --- method entered");
            Optional<Hospital> hospital = hospitalRepository.findById(Objects.requireNonNull(hospitalID));
            Doctor newDoctor = new Doctor();
            newDoctor.setId(Objects.requireNonNull(id));
            newDoctor.setFullName(Objects.requireNonNull(name));
            newDoctor.setPracticingSince(Objects.requireNonNull(practicingSince));
            newDoctor.setHospital(hospital.orElseThrow(() -> new InvalidIDException("There is no hospital with the given ID")));
            log.trace("addDoctor --- method exited");

            return doctorRepository.existsById(id)? null: doctorRepository.save(newDoctor);
        }catch(NullPointerException elementWasNull){
            throw new NullArgumentException("Argument of addDoctor was null.");
        }
    }
    /**
     *
     * @param id - identifier of the doctor the is to be retrieved
     * @return the doctor object if it exists or null if there is no doctor with the given id
     * @throws NullArgumentException - if the given id is null
     */
    public Doctor getDoctor(Integer id) throws NullArgumentException{
        try{
            log.trace("getDoctor --- method entered");

            return doctorRepository.findById(Objects.requireNonNull(id))
                    .orElse(null);
        }catch(NullPointerException argumentWasNull){
            throw new NullArgumentException("Argument for getDoctor was null.\n");
        }finally {
            log.trace("getDoctor --- method exited");

        }
    }

    /**
     *
     * @return all the doctors in the application
     */
    public Collection<Doctor> getAllDoctors(){
        log.trace("getAllDoctors --- method entered");

       var doctors =doctorRepository.findAllWithHospitalCriteria();
        log.trace("getAllDoctors --- method exited");

        return doctors;
    }


    /**
     *
     * @param minYearsOfExperience - the minimum number of years of experience required
     * @return - doctors that have at least the minimum number of years of experience
     * @throws - NullArgumentException if null is given as a parameter
     */
    public List<Doctor> getDoctorsWithMinimumExperience(Integer minYearsOfExperience) throws NullArgumentException{
        try{
            log.trace("getDoctorsWithMinimumExperience --- method entered");

            Objects.requireNonNull(minYearsOfExperience);
            LocalDate criteria = LocalDate.now().minusYears(minYearsOfExperience);
            Collection<Doctor> doctors = doctorRepository.findAllByPracticingSinceBefore(criteria);
            log.trace("getDoctorsWithMinimumExperience --- method exited");

            return (List<Doctor>) doctors;

        }catch (NullPointerException nullArgumentGiven){
            throw new NullArgumentException("Null was passed as an argument");
        }
    }

    /**
     *
     * @param doctorID id of the doctor to be updated
     * @param newName optional containing the new name of the doctor
     * @param newHospitalID optional containing the id of the new hospital of the doctor
     * @return the update doctor
     * @throws InvalidIDException if a invalid doctor id or hospital id are given
     * @throws ValidationException if the new data is invalid
     */
    public Doctor updateDoctor(Integer doctorID, String newName, Integer newHospitalID,
                               LocalDate newPracticingSince )
            throws InvalidIDException, ValidationException {
        try{
            log.trace("updateDoctor --- method entered");

            Objects.requireNonNull(doctorID);
            Objects.requireNonNull(newName);
            Objects.requireNonNull(newHospitalID);
            Objects.requireNonNull(newPracticingSince);

            Doctor doctor = doctorRepository.findById(doctorID).orElseThrow(
                    () -> new InvalidIDException("Invalid doctor ID received as parameter")
            );


            Doctor updatedDoctor = new Doctor();
            updatedDoctor.setId(doctorID);
            updatedDoctor.setFullName(newName);
            updatedDoctor.setPracticingSince(newPracticingSince);
            updatedDoctor.setHospital(doctor.getHospital());


            Hospital newHospital = hospitalRepository.findById(newHospitalID).orElseThrow(
                    () -> new InvalidIDException("Invalid Hospital ID received as parameter"));
            updatedDoctor.setHospital(newHospital);

            log.trace("updateDoctor --- method exited");

            return doctorRepository.save(updatedDoctor);


        }catch (NullPointerException argumentWasNull){
            throw new NullArgumentException("Argument for updateDoctor was null.");
        }
    }

    /**
     *
     * @param doctorID - the id of the doctor to be removed
     * @return the deleted doctor or null if there was no doctor with the given id
     * @throws NullArgumentException is the given argument is null
     */
    public Doctor deleteDoctor(Integer doctorID){
        try{
            log.trace("deleteDoctor --- method entered");

            Objects.requireNonNull(doctorID);
            Doctor doctorToBeDeleted = doctorRepository.findById(doctorID).orElse(null);
            if(doctorToBeDeleted == null)
                return null;
            doctorRepository.deleteById(doctorID);
            log.trace("deleteDoctor --- method entered");

            return doctorToBeDeleted;
        }catch (NullPointerException argumentWasNull){
            throw new NullArgumentException("Argument for deleteDoctor was null.");
        }
    }

    /**
     *
     * @return a map that maps each hospital to a collections with all the doctors that work for that hospital
     */
    public Collection<Hospital> groupDoctorsByHospital(){
        log.trace("groupDoctorsByHospital --- method entered");

        Collection<Doctor> doctors = doctorRepository.findAllWithHospitalSQL();
        Collection<Hospital> hospitals = new HashSet<>();
        doctors.forEach(doctor -> {
            if (!hospitals.contains(doctor.getHospital())) {
                hospitals.add(doctor.getHospital());
            }
        });

        log.trace("groupDoctorsByHospital --- method exited");

        return hospitals;
    }


}
