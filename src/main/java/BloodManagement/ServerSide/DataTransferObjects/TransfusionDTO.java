package BloodManagement.ServerSide.DataTransferObjects;

import lombok.*;
import org.springframework.data.repository.NoRepositoryBean;

@Data
@NoRepositoryBean
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class TransfusionDTO extends BaseDto {
    private Integer donorID;
    private Integer receiverID;
    private Integer doctorID;
    private Integer units;
}
