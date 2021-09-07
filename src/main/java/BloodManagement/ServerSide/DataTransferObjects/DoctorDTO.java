package BloodManagement.ServerSide.DataTransferObjects;

import BloodManagement.ServerSide.Domain.Hospital;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class DoctorDTO extends BaseDto {
    private String fullName;
    private String practicingSince;
    private Integer hospitalId;
    private Hospital hospital;
}
