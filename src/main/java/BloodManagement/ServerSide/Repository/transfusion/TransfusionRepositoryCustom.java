package BloodManagement.ServerSide.Repository.transfusion;

import BloodManagement.ServerSide.Domain.Transfusion;

import java.util.Collection;

public interface TransfusionRepositoryCustom {
    Collection<Transfusion> getAllWithEveryAttributeJPQL();
    Collection<Transfusion> getAllWithEveryAttributeCriteria();
    Collection<Transfusion> getAllWithEveryAttributeSQL();

    Collection<Transfusion> getAllWithDonorAndDoctorJPQL();
    Collection<Transfusion> getAllWithDonorAndDoctorCriteria();
    Collection<Transfusion> getAllWithDonorAndDoctorSQL();
}
