package BloodManagement.ServerSide.Repository.hospital;

import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Hospital;

import java.util.Collection;
import java.util.List;

public interface HospitalRepositoryCustom {
    Collection<Hospital> findAllWithDoctorsJPQL();
    Collection<Hospital> findAllWithDoctorsCriteria();
    Collection<Hospital> findAllWithDoctorsSQL();
}
