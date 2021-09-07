package BloodManagement.ServerSide.Repository.doctor;

import BloodManagement.ServerSide.Domain.Doctor;

import java.util.Collection;

public interface DoctorRepositoryCustom {
    Collection<Doctor> findAllWithHospitalJPQL();
    Collection<Doctor> findAllWithHospitalCriteria();
    Collection<Doctor> findAllWithHospitalSQL();
}
