package BloodManagement.ServerSide.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Data
@AllArgsConstructor
public class TransfusionsDoctorsDto{
    public List<DoctorReportDTO> doctors;

    public TransfusionsDoctorsDto(){
        this.doctors = new ArrayList<>();
    }

    public void addDoctor(DoctorDTO doctor,PersonsDTO persons){
        this.doctors.add(new DoctorReportDTO(doctor,persons));
    }
}

@EqualsAndHashCode
@Data
@AllArgsConstructor
@NoArgsConstructor
class DoctorReportDTO{
    public DoctorDTO doctor;
    public PersonsDTO persons;
}
