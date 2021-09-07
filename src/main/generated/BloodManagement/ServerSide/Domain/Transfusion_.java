package BloodManagement.ServerSide.Domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transfusion.class)
public abstract class Transfusion_ extends BloodManagement.ServerSide.Domain.BaseEntity_ {

	public static volatile SingularAttribute<Transfusion, Doctor> doctor;
	public static volatile SingularAttribute<Transfusion, Person> donor;
	public static volatile SingularAttribute<Transfusion, Person> receiver;
	public static volatile SingularAttribute<Transfusion, Integer> units;

}

