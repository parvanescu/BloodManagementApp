package BloodManagement.ServerSide.Domain;


import lombok.*;

import javax.persistence.*;

/**
 * @author Marin Peptenaru
 * Transfusion class for representing blood being transfused from a Person to another
 */
@NamedEntityGraphs({
        @NamedEntityGraph(name= "transfusionWithDoctor",
                attributeNodes = @NamedAttributeNode(value="doctor")),
        @NamedEntityGraph(name= "transfusionWithDonor",
                attributeNodes = @NamedAttributeNode(value="donor")),
        @NamedEntityGraph(name = "transfusionWithReceiver",
                attributeNodes = @NamedAttributeNode(value = "receiver"))

        })

@Entity
@Table
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@ToString
public class Transfusion extends BaseEntity<Integer> {

    /**
     * idCounter - used to assign an unique Integer ID to each Transfusion Object.
     * This optimises equality checking and relieves the user from having to worry about ID duplication.
     * IDs are generated incrementally starting from 1.
     * **/

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Person donor ;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Person receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Doctor doctor;
    private  Integer units;


    public Transfusion(){
        id = -1;
        donor = new Person();
        receiver = new Person();
        doctor = new Doctor();
        units = -1;
    }

    /**
     * Constructor
     * @param donor : Person - the person that donates blood
     * @param receiver : Person - the person that receives blood
     * @param doctor : Doctor - the doctor that performed the blood transfusion
     * @param units : Integer - the units of blood that are transfused from donor to receiver  **/
    public Transfusion(Integer id, Person donor, Person receiver, Doctor doctor, Integer units) {
        super(id);
        this.donor = donor;
        this.receiver = receiver;
        this.doctor = doctor;
        this.units = units;
        this.id = id;
    }
}
