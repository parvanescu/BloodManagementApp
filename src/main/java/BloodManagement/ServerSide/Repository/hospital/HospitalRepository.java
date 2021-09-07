package BloodManagement.ServerSide.Repository.hospital;

import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Hospital;
import BloodManagement.ServerSide.Repository.ORMRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface HospitalRepository extends ORMRepository<Integer, Hospital> {

    @EntityGraph(value = "hospitalWithDoctors", type = EntityGraph.EntityGraphType.LOAD)
    Collection<Hospital> findAllByCapacityGreaterThanEqual(Integer minCapacity);

    @Query("select distinct d from Hospital d")
    @EntityGraph(value = "hospitalWithDoctors", type = EntityGraph.EntityGraphType.LOAD)
    Collection<Hospital> getAllWithDoctors();
}
