package BloodManagement.ServerSide.DataTransferObjects;

import lombok.*;
import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@NoRepositoryBean
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class PrescriptionDTO extends BaseDto {
    private Integer doctorID;
    private Integer personID;
    private ArrayList<Integer> drugs;
    private LocalDate releaseDate;

}