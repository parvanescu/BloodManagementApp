package BloodManagement.ServerSide.DataTransferObjects;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class PersonDTO extends BaseDto{
    private String fullName;
    private Double weight;
    private LocalDate dateOfBirth;
    private String bloodType;
}
