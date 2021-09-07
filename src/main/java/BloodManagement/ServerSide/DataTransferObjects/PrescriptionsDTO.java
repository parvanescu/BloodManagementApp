package BloodManagement.ServerSide.DataTransferObjects;

import BloodManagement.ServerSide.Domain.Prescription;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrescriptionsDTO extends BaseDto {
    private Set<PrescriptionDTO> prescriptions;
}
