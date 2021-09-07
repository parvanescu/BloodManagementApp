package BloodManagement.ServerSide.Domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Parvanescu Stefan
 * Prescription class.
 * It holds a drugs list for a certain person prescriptied by a certain doctor at a certain date
 * **/
@NamedEntityGraphs({
        @NamedEntityGraph(name="prescriptionWithDoctor",
        attributeNodes = @NamedAttributeNode(value = "doctor")),
        @NamedEntityGraph(name="prescriptionWithDoctorAndHospital",
        attributeNodes = @NamedAttributeNode(value = "doctor",subgraph = "doctorWithHospital"),
        subgraphs = @NamedSubgraph(name="doctorWithHospital",
        attributeNodes = @NamedAttributeNode(value = "hospital"))),
        @NamedEntityGraph(name="prescriptionWithDrugs",
        attributeNodes = @NamedAttributeNode(value = "drugs"))
}
)
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@ToString(exclude = "drugs")
public class Prescription extends BaseEntity<Integer> {
    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Person person;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "prescribedDrugs", joinColumns = @JoinColumn(name = "drug_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "prescription_id",referencedColumnName = "id"))
    private List<Drug> drugs;
    private LocalDate releaseDate;


    public ArrayList<Integer> getAllDrugIds() {
        ArrayList<Integer> drugIds = (ArrayList<Integer>) this.drugs.stream()
                .map(Drug::getId)
                .collect(Collectors.toList());
        return drugIds;
    }
}
