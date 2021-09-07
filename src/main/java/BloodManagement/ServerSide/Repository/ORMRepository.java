package BloodManagement.ServerSide.Repository;

import BloodManagement.ServerSide.Domain.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface ORMRepository<ID extends Serializable, T extends BaseEntity<ID>>
extends JpaRepository<T, ID> {
}
