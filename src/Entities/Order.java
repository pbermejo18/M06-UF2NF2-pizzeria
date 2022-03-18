package Entities;

//import Entities.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @Column(name = "idorder")
    int idOrder;
    @Column(name = "orderdate")
    Date orderDate;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idcustomer")
    public Entities.Customer customer;

    //@OneToMany(mappedBy="order", orphanRemoval=true, cascade={CascadeType.ALL})
    @Transient
    Set<OrdersDetail> ordersdetails = new HashSet<>();


    public Order(int idOrder, Date orderDate, Customer customer) {
        super();
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.customer = customer;
    }

    public Order() {
        super();
    }

    public void setIdOrder(int idOrder) { this.idOrder = idOrder; }
    public int getIdOrder() { return idOrder; }

    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public Date getOrderDate() { return orderDate; }

    public void setIdCustomer(Entities.Customer customer) { this.customer = customer; }
    public Entities.Customer getIdCustomer() { return customer; }

    public void addOrderDetail(OrdersDetail ordersDetail){
        ordersdetails.add(ordersDetail);
    }
    public OrdersDetail getOrderDetail(int i){
        Iterator<OrdersDetail> it = ordersdetails.iterator();
        for(int j = 0; j < i; j++)
            it.next();
        return it.next();
    }

    public void setOrdersdetails(Set<OrdersDetail> ordersdetails) {
        this.ordersdetails = ordersdetails;
    }

    public Set<OrdersDetail> getOrdersdetails() {
        return ordersdetails;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", orderDate=" + orderDate +
                //", customer=" + customer +
                //", ordersdetails=" + ordersdetails +
                '}';
    }
}
