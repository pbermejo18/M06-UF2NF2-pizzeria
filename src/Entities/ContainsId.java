package Entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContainsId implements Serializable {
    private int idingredient;
    private int idpizza;

    // default constructor

    public ContainsId(int idingredient, int idpizza) {
        this.idingredient = idingredient;
        this.idpizza = idpizza;
    }

    // getters, equals() and hashCode() methods

    public void setIdingredient(int idingredient) {
        this.idingredient = idingredient;
    }

    public int getIdingredient() {
        return idingredient;
    }

    public void setIdpizza(int idpizza) {
        this.idpizza = idpizza;
    }

    public int getIdpizza() {
        return idpizza;
    }
}
