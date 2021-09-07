package BloodManagement.ServerSide.Domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ extends BloodManagement.ServerSide.Domain.BaseEntity_ {

	public static volatile SingularAttribute<Person, String> fullName;
	public static volatile SingularAttribute<Person, Double> weight;
	public static volatile SingularAttribute<Person, LocalDate> dateOfBirth;
	public static volatile SingularAttribute<Person, BloodType> bloodType;

}

