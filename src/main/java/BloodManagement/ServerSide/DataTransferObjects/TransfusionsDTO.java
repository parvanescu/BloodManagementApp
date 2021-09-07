package BloodManagement.ServerSide.DataTransferObjects;

import BloodManagement.ServerSide.Domain.Transfusion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransfusionsDTO extends BaseDto {
    private Set<TransfusionDTO> transfusions;
}
