package BloodManagement.ServerSide.DataTransferObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonsDTO {
    private Set<PersonDTO> persons;

}
