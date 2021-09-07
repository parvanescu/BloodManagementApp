package BloodManagement.ServerSide.Domain;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DatabaseSetup
@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Drug extends BaseEntity<Integer> {
    private String name;
    private Integer recommendedAmount;
}
