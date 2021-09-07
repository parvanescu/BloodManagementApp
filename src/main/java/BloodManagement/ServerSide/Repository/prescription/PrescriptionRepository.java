package BloodManagement.ServerSide.Repository.prescription;

import BloodManagement.ServerSide.Domain.Prescription;
import BloodManagement.ServerSide.Repository.CustomRepositorySupport;
import BloodManagement.ServerSide.Repository.ORMRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface PrescriptionRepository extends ORMRepository<Integer, Prescription>,PrescriptionRepositoryCustom {
    @Query("select distinct p from Prescription p")
    @EntityGraph(value="prescriptionWithDrugs",type = EntityGraph.EntityGraphType.LOAD)
    Collection<Prescription> findAllWithDrugs();

    @EntityGraph(value = "prescriptionWithDrugs",type = EntityGraph.EntityGraphType.LOAD)
    Collection<Prescription> findAllByReleaseDateEquals(LocalDate date);

    @Query("select distinct p from Prescription p")
    @EntityGraph(value = "prescriptionWithDoctor",type= EntityGraph.EntityGraphType.LOAD)
    Collection<Prescription> findAllWithDoctor();

    @Query("select distinct p from Prescription p")
    @EntityGraph(value = "prescriptionWithDoctorAndHospital",type= EntityGraph.EntityGraphType.LOAD)
    Collection<Prescription> findAllWithDoctorAndHospital();
}
