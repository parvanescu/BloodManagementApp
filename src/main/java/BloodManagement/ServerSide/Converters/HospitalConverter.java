package BloodManagement.ServerSide.Converters;


import BloodManagement.ServerSide.DataTransferObjects.DoctorDTO;
import BloodManagement.ServerSide.DataTransferObjects.DoctorsDTO;
import BloodManagement.ServerSide.DataTransferObjects.HospitalDTO;
import BloodManagement.ServerSide.Domain.Hospital;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * Created by radu.
 */
@Component
public class HospitalConverter implements Converter<Hospital, HospitalDTO>{
    @Override
    public Hospital convertDtoToModel(HospitalDTO dto) {
        Hospital hospital = new Hospital();
        hospital.setId(dto.getId());
        hospital.setCapacity(dto.getCapacity());
        hospital.setName(dto.getName());
        hospital.setAddress(dto.getAddress());
        return hospital;
    }

    @Override
    public HospitalDTO convertModelToDto(Hospital hospital) {
        HospitalDTO hospitalDTO =  new HospitalDTO();
        hospitalDTO.setId(hospital.getId());
        hospitalDTO.setCapacity(hospital.getCapacity());
        hospitalDTO.setName(hospital.getName());
        hospitalDTO.setAddress(hospital.getAddress());
        if(hospital.getDoctors().size() == 0){
            hospitalDTO.setDoctors(null);
            return hospitalDTO;
        }
        DoctorsDTO doctorsDTO = new DoctorsDTO();
        doctorsDTO.setDoctors(new HashSet<>());
        hospital.getDoctors().forEach(doctor ->{
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setId(doctor.getId());
            doctorDTO.setFullName(doctor.getFullName());
            doctorDTO.setPracticingSince(doctor.getPracticingSince().toString());
            doctorDTO.setHospitalId(hospital.getId());
            doctorsDTO.getDoctors().add(doctorDTO);
        });
        hospitalDTO.setDoctors(doctorsDTO);

        return hospitalDTO;
    }
}