package BloodManagement.ServerSide.Repository.prescription;

import BloodManagement.ServerSide.Domain.Prescription;

import java.time.LocalDate;
import java.util.Collection;

public interface PrescriptionRepositoryCustom {
    Collection<Prescription> findAllWithDrugsJPQL();
    Collection<Prescription> findAllWithDrugsCriteria();
    Collection<Prescription> findAllWithDrugsSQL();

    Collection<Prescription> findByMonthWithDoctorJPQL(LocalDate date);
    Collection<Prescription> findByMonthWithDoctorCriteria(LocalDate date);
    Collection<Prescription> findByMonthWithDoctorSQL(LocalDate date);
}
