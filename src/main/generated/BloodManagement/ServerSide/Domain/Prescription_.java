package BloodManagement.ServerSide.Domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Prescription.class)
public abstract class Prescription_ extends BloodManagement.ServerSide.Domain.BaseEntity_ {

	public static volatile SingularAttribute<Prescription, Doctor> doctor;
	public static volatile ListAttribute<Prescription, Drug> drugs;
	public static volatile SingularAttribute<Prescription, LocalDate> releaseDate;
	public static volatile SingularAttribute<Prescription, Person> person;

}

