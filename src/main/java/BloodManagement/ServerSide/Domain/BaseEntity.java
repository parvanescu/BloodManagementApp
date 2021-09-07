package BloodManagement.ServerSide.Domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Marin Peptenaru
 * Generic base class for all entities
* */

@MappedSuperclass

public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    @Id
    @Column(name = "id")
    protected ID id;

    public BaseEntity(){id = null;}

    public BaseEntity(ID id) {
        this.id = id;
    }

    public ID getId(){
        return this.id;
    }
    /**
     * Should be implemented in order to copy non-final fields from another entity
     * @param other - entity to copy from
    **/
    public BaseEntity<ID> copyOther(BaseEntity<ID> other){return null;}

    public void setId(ID id) {this.id = id;}
    /**
     * At this level, equality, hashing and string formatting depend only on the id.
    * */
    @Override
    public boolean equals(Object obj) {
        // check that obj is a BaseEntity Object
        if(!(obj instanceof BaseEntity)) return false;
        return ((BaseEntity<ID>)obj).id.equals(this.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
