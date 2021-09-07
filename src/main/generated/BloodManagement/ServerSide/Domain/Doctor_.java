package BloodManagement.ServerSide.Domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Doctor.class)
public abstract class Doctor_ extends BloodManagement.ServerSide.Domain.BaseEntity_ {

	public static volatile SingularAttribute<Doctor, LocalDate> practicingSince;
	public static volatile SingularAttribute<Doctor, String> fullName;
	public static volatile SingularAttribute<Doctor, Hospital> hospital;

}

