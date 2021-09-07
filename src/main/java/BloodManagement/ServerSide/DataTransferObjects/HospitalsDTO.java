package BloodManagement.ServerSide.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HospitalsDTO extends BaseDto{
    private Set<HospitalDTO> hospitals;

}
