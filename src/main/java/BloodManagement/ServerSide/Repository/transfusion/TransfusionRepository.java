package BloodManagement.ServerSide.Repository.transfusion;

import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Transfusion;
import BloodManagement.ServerSide.Repository.ORMRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedEntityGraph;
import java.util.Collection;

@Repository
public interface TransfusionRepository extends ORMRepository<Integer, Transfusion>,TransfusionRepositoryCustom {

    @EntityGraph(value = "transfusionWithDoctor",type = EntityGraph.EntityGraphType.LOAD)
    Collection<Transfusion> findAllByDoctorEquals(Doctor doctor);

    @Query("select distinct t from Transfusion t")
    @EntityGraph(value = "transfusionWithDonor",type = EntityGraph.EntityGraphType.LOAD)
    Collection<Transfusion> findAllWithDonor();

    @Query("select distinct t from Transfusion t")
    @EntityGraph(value = "transfusionWithReceiver",type = EntityGraph.EntityGraphType.LOAD)
    Collection<Transfusion> findAllWithReceiver();
}
