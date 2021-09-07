package BloodManagement.ServerSide.Domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Hospital.class)
public abstract class Hospital_ extends BloodManagement.ServerSide.Domain.BaseEntity_ {

	public static volatile SingularAttribute<Hospital, String> address;
	public static volatile ListAttribute<Hospital, Doctor> doctors;
	public static volatile SingularAttribute<Hospital, String> name;
	public static volatile SingularAttribute<Hospital, Integer> capacity;

}

