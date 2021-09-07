package BloodManagement.ServerSide.Repository.person;

import BloodManagement.ServerSide.Domain.Person;
import BloodManagement.ServerSide.Repository.ORMRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepository extends ORMRepository<Integer, Person> {
    Collection<Person> findByOrderByWeightAsc();
    Collection<Person> getAllByWeightGreaterThan(Double minWeight);
}
