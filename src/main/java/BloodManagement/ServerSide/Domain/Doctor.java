package BloodManagement.ServerSide.Domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Marin Peptenaru
 * **/
@NamedEntityGraph(name = "doctorWithHospital",attributeNodes = @NamedAttributeNode(value = "hospital"))
@Entity
@Table
@ToString(exclude = {"hospital"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"hospital"}, callSuper = true)
@Data
@Builder
public class Doctor extends BaseEntity<Integer>{
    private String fullName;

    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDate practicingSince;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Hospital hospital;

}
