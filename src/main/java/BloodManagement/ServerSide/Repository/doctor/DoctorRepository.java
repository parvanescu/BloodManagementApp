package BloodManagement.ServerSide.Repository.doctor;

import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Repository.ORMRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface DoctorRepository extends ORMRepository<Integer, Doctor>,DoctorRepositoryCustom {

    @Query("select distinct d from Doctor d")
    @EntityGraph(value = "doctorWithHospital",type = EntityGraph.EntityGraphType.LOAD)
    Collection<Doctor> findAllByPracticingSinceBefore(LocalDate practicingSince);

    @Query("select distinct d from Doctor d")
    @EntityGraph(value = "doctorWithHospital",type = EntityGraph.EntityGraphType.LOAD)
    Collection<Doctor> findAllWithHospital();


}
