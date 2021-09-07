package BloodManagement.ServerSide.DataTransferObjects;

import BloodManagement.ServerSide.Domain.Doctor;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class HospitalDTO extends BaseDto {
    private Integer capacity;
    private String name;
    private String address;
    private DoctorsDTO doctors;
}
