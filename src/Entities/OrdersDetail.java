package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ordersdetails")
public class OrdersDetail implements Serializable {
    @EmbeddedId
    private OrdersDetailsId detailsId;

    @Column(name = "quantity")
    int quantity;
    @Column(name = "priceeach")
    float priceEach;

    //@ManyToOne(fetch=FetchType.LAZY)
    //@JoinColumn(name="idpizza")
    @Transient
    Pizza pizza;
    //@ManyToOne(fetch=FetchType.LAZY)
    //@JoinColumn(name="idorder")
    @Transient
    Order order;

    public OrdersDetail(int quantity, float priceEach, Pizza pizza, Order order) {
        super();
        this.quantity = quantity;
        this.priceEach = priceEach;
        this.pizza = pizza;
        this.order = order;
        detailsId = new OrdersDetailsId(pizza.getIdPizza(), order.getIdOrder());
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

    public void setIdOrder(Entities.Order order) { this.order = order; this.detailsId.setIdorder(order.getIdOrder()); }
    public Entities.Order getIdOrder() { return order; }

    @Override
    public String toString() {
        return "OrdersDetail{" +
                "quantity=" + quantity +
                ", priceEach=" + priceEach +
                ", pizza=" + pizza +
                '}';
    }
}