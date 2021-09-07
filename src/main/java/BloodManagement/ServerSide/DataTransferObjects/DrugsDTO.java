package BloodManagement.ServerSide.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugsDTO extends BaseDto {
    private Set<DrugDTO> drugs;
}
