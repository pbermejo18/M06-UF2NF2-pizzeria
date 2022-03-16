package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ordersdetails")
public class OrdersDetail implements Serializable {
    @EmbeddedId
    private DetailsId detailsId;

    @Column(name = "quantity")
    int quantity;
    @Column(name = "priceeach")
    float priceEach;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idPizza")
    Pizza pizza;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idOrder")
    Customer order;

    public OrdersDetail(int quantity, float priceEach, Pizza pizza, Customer order) {
        super();
        this.quantity = quantity;
        this.priceEach = priceEach;
        this.pizza = pizza;
        this.order = order;
        detailsId = new DetailsId(pizza.getIdPizza(), order.getOrders().size());
    }

    public OrdersDetail(){
        super();
    }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getQuantity() { return quantity; }

    public void setPriceEach(float priceEach) { this.priceEach = priceEach; }
    public float getPriceEach() { return priceEach; }

    public void setIdPizza(Entities.Pizza pizza) { this.pizza = pizza; this.detailsId.setIdpizza(pizza.getIdPizza());}
    public Entities.Pizza getIdPizza() { return pizza; }

    public void setIdOrder(Entities.Customer order) { this.order = order; this.detailsId.setIdorder(order.getOrders().size()); }
    public Entities.Customer getIdOrder() { return order; }

    @Override
    public String toString() {
        return "OrdersDetail{" +
                "quantity=" + quantity +
                ", priceEach=" + priceEach +
                ", pizza=" + pizza +
                '}';
    }
}

@Embeddable
class DetailsId implements Serializable {
    private int idpizza;
    private int idorder;

    // default constructor
    public DetailsId(int idpizza, int idorder) {
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
