package BloodManagement.ServerSide.DataTransferObjects;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class DrugDTO extends BaseDto {
    private String name;
    private Integer recommendedAmount;
}
