package BloodManagement.ServerSide.Repository.drug;

import BloodManagement.ServerSide.Domain.Drug;
import BloodManagement.ServerSide.Repository.ORMRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DrugRepository extends ORMRepository<Integer, Drug> {
    Collection<Drug> findAllByRecommendedAmountGreaterThanEqual(Integer amount);
}
