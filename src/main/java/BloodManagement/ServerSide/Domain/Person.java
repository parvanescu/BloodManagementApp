package BloodManagement.ServerSide.Domain;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author Marin Peptenaru
 * Person class for representing potential donors and receivers of blood
 * **/
@Entity
@Table
public class Person extends BaseEntity<Integer> {

    /**
     * idCounter is used to assign an unique Integer ID to each person.
     * This way, the user does need to worry about ID duplication and their uniqueness.
     * IDs are generated incrementally starting from 1.
     * **/

    protected String fullName;
    protected Double weight;
    protected LocalDate dateOfBirth;
    protected BloodType bloodType;

    public Person(){
        this.id = -1;
        fullName ="_";
        weight = -1.0;
        dateOfBirth = LocalDate.now();
        bloodType = BloodType.AB_POS;
    }

    public Person(Integer id, String fullName, Double weight, LocalDate dateOfBirth, BloodType bloodType) {
        super(id);
        this.fullName = fullName;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;

    }

    @Override
    public Person copyOther(BaseEntity<Integer> other) {
        if(other instanceof Person){
            Person otherPerson = (Person) other;
            fullName = otherPerson.fullName;
            dateOfBirth = otherPerson.dateOfBirth;
            weight = otherPerson.weight;
            bloodType = otherPerson.bloodType;
        }
        return this;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public String getFullName() {
        return fullName;
    }

    public Double getWeight() {
        return weight;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return  super.toString() + " | " +
                fullName + " | " +
                bloodType + " | " +
                weight + " | " +
                dateOfBirth + '\n';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Person && super.equals(obj);
    }
}
