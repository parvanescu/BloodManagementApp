package BloodManagement.ServerSide.Domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Petrus Andrei
 * **/
@NamedEntityGraph(name = "hospitalWithDoctors",
attributeNodes = @NamedAttributeNode(value = "doctors"))
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@ToString(exclude = "doctors")
public class Hospital extends BaseEntity<Integer>{

    private Integer capacity;
    private String name;
    private String address;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hospital",fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();

    }
