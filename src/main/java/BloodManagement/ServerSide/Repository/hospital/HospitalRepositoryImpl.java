package BloodManagement.ServerSide.Repository.hospital;

import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Hospital;
import BloodManagement.ServerSide.Repository.CustomRepositorySupport;

import java.util.Collection;



public class HospitalRepositoryImpl extends CustomRepositorySupport implements HospitalRepositoryCustom {

    @Override
    public Collection<Hospital> findAllWithDoctorsJPQL() {
        return null;
    }

    @Override
    public Collection<Hospital> findAllWithDoctorsCriteria() {
        return null;
    }

    @Override
    public Collection<Hospital> findAllWithDoctorsSQL() {
        return null;
    }
}
