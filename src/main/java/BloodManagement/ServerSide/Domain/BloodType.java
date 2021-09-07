package BloodManagement.ServerSide.Domain;


import BloodManagement.ServerSide.CoreExceptions.ValidationException;

import java.io.Serializable;
import java.util.*;

/**
 * @author Marin Peptenaru
 * BloodType data type
 * **/

public class BloodType implements Serializable {
    // labels for blood types
    public enum Type {
        A, B, AB, O;

        @Override
        public String toString() {
            return super.toString();
        }

        // every type can donate to itself
        // O can donate to anyone
        // A and B can also donate to AB
        // AB can ONLY donate to itself
        public boolean canDonateTo(Type other){
            if(this == other || this == O) return true;
            if(this == A || this == B) return other == AB;
            return false;
        }

        // every type can receive from itself
        // A and B can also receive from O
        // O can ONLY receive from itself
        // AB can receive from anyone
        public boolean canReceiveFrom(Type other){
            if(this == other || this == AB) return true;
            if(this == A || this == B) return other == O;
            return false;
        }
    }

    // labels for rh
    public enum Rh{
        POS, NEG;

        @Override
        public String toString() {
            return this == POS? "+" : "-";
        }

        // NEG can donate to both POS and NEG, POS only to itself
        public boolean canDonateTo(Rh other){
            return other == POS || this == other;
        }

        // POS can receive from both, NEG only from itself
        public boolean canReceiveFrom(Rh other){
            return this == POS || other == NEG;
        }
    }

    private final Type type;
    private final Rh rh;



    private BloodType(Type type, Rh rh){ // private constructor so that we do not have redundant objects that are not needed
        this.type = type;
        this.rh = rh;
    }

    // final static instances, one for each possible type and rh combination
    public final static BloodType AB_POS = new BloodType(Type.AB, Rh.POS);
    public final static BloodType A_POS = new BloodType(Type.A, Rh.POS);
    public final static BloodType B_POS = new BloodType(Type.B, Rh.POS);
    public final static BloodType O_POS = new BloodType(Type.O, Rh.POS);
    public final static BloodType AB_NEG = new BloodType(Type.AB, Rh.NEG);
    public final static BloodType A_NEG = new BloodType(Type.A, Rh.NEG);
    public final static BloodType B_NEG = new BloodType(Type.B, Rh.NEG);
    public final static BloodType O_NEG = new BloodType(Type.O, Rh.NEG);
    private final static Set<BloodType> bloodTypes = new HashSet<>();

    // map used to map strings to their respective BloodType object
    private final static Map<String, BloodType> stringTypes = new HashMap<>();

    // initializer block for String -> BloodType map
    static{
        stringTypes.put(AB_POS.toString(), AB_POS);
        stringTypes.put(A_POS.toString(), A_POS);
        stringTypes.put(B_POS.toString(), B_POS);
        stringTypes.put(O_POS.toString(), O_POS);
        stringTypes.put(AB_NEG.toString(), AB_NEG);
        stringTypes.put(A_NEG.toString(), A_NEG);
        stringTypes.put(B_NEG.toString(), B_NEG);
        stringTypes.put(O_NEG.toString(), O_NEG);
        bloodTypes.addAll(Arrays.asList(AB_POS, A_POS, B_POS, O_POS, AB_NEG, A_NEG, B_NEG, O_NEG));
    }

    // static function for converting a string to its corresponding BloodType object
    /**
     * @param str - a string that should be the string representation of a BloodType object
     * @return BloodType - the corresponding BloodType object for the given string representation
     * @throws ValidationException if the given string is not a valid string representation of a BloodType object
     **/
    public static BloodType parseBloodType(String str) throws ValidationException {
        try{
            return Objects.requireNonNull(
                    stringTypes.getOrDefault(str, null)
            );
        }catch(NullPointerException e){
            throw new ValidationException("Invalid string passed as blood type.");
        }
    }


    // getters, equals, toStr

    public Type getType() {
        return type;
    }

    public Rh getRh() {
        return rh;
    }



    /** check that both type and rh are compatible for transfusion from this to other
     * @param other - represents the blood type of a potential receiver
     * @return boolean - true if other can receive blood from this, false otherwise
     * **/
    public boolean canDonateTo(BloodType other){
        return type.canDonateTo(other.type) && rh.canDonateTo(other.rh);
    }

    /** check that both type and rh are compatible for transfusion from other to this
     * @param other - represents the blood type of a potential donor
     * @return boolean - true if this can receive blood from other, false otherwise
     * **/
    public boolean canReceiveFrom(BloodType other){
        return type.canReceiveFrom(other.type) && rh.canReceiveFrom(other.rh);
    }

    public static Collection<BloodType> getBloodTypes(){
        return bloodTypes;
    }


    // BloodTypes are equal only if they have both the same type and rh
    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof  BloodType)) return false;
        BloodType other = (BloodType) obj ;
        return other.type == this.type && this.rh == other.rh;
    }

    @Override
    public String toString() {
        return this.type.toString() + this.rh;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, rh);
    }
}
