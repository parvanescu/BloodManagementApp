package BloodManagement.ServerSide.DataTransferObjects;

import BloodManagement.ServerSide.Domain.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@EqualsAndHashCode
@Data
@AllArgsConstructor
public class TransfusionsReportDTO {
    public TransfusionsDoctorsDto reportDTO;

    public TransfusionsReportDTO(){
        this.reportDTO = new TransfusionsDoctorsDto();
    }
}



