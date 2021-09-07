package BloodManagement.ServerSide.Converters;

import BloodManagement.ServerSide.DataTransferObjects.DoctorDTO;
import BloodManagement.ServerSide.Domain.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorConverter implements Converter<Doctor, DoctorDTO> {
    @Override
    public Doctor convertDtoToModel(DoctorDTO dto) {
        return null;
    }

    @Override
    public DoctorDTO convertModelToDto(Doctor doctor) {
        DoctorDTO doc = new DoctorDTO(doctor.getFullName(),doctor.getPracticingSince().toString(),doctor.getHospital().getId(),doctor.getHospital());
        doc.setId(doctor.getId());
        return doc;
    }

    public DoctorDTO convertReportModelToDto(Doctor doctor){
        DoctorDTO doc = new DoctorDTO();
        doc.setId(doctor.getId());
        doc.setFullName(doctor.getFullName());
        doc.setPracticingSince(doctor.getPracticingSince().toString());
        return doc;
    }
}
