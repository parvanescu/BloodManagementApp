package BloodManagement.ServerSide.Domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Drug.class)
public abstract class Drug_ extends BloodManagement.ServerSide.Domain.BaseEntity_ {

	public static volatile SingularAttribute<Drug, String> name;
	public static volatile SingularAttribute<Drug, Integer> recommendedAmount;

}

