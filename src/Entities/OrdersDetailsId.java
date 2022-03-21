package Entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrdersDetailsId implements Serializable {
    private int idpizza;
    private int idorder;

    // default constructor
    public OrdersDetailsId(int idpizza, int idorder) {
        this.idpizza = idpizza;
        this.idorder = idorder;
    }

    // getters, equals() and hashCode() methods
    public void setIdpizza(int idpizza) {
        this.idpizza = idpizza;
    }
    public int getIdpizza() {
        return idpizza;
    }

    public void setIdorder(int idorder) { this.idorder = idorder; }
    public int getIdorder() { return idorder; }
}
