package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @Column(name = "idcustomer")
    int idCustomer;
    @Column(name = "name")
    String name;
    @Column(name = "address")
    String address;
    @Column(name = "email")
    String email;
    @Column(name = "phone")
    String phone;

    @OneToMany(mappedBy="customer", orphanRemoval=true, cascade={CascadeType.ALL})
    Set<Order> orders = new HashSet<>();

    public Customer(int idCustomer, String name, String address, String email, String phone) {
        super();
        this.idCustomer = idCustomer;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Customer(){
        super();
    }

    public void setIdCustomer(int idCustomer) { this.idCustomer = idCustomer; }
    public int getIdCustomer() { return idCustomer; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setAddress(String address) { this.address = address; }
    public String getAddress() { return address; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setPhone(String phone) { this.phone = phone; }
    public String getPhone() { return phone; }

    public void addOrder(Order order){
        orders.add(order);
    }
    public Order getOrder(int i){
        Iterator<Order> it = orders.iterator();
        for(int j = 0; j < i; j++)
            it.next();
        return it.next();
    }

    public void setOrders(Set<Order> orders) { this.orders = orders; }
    public Set<Order> getOrders() { return orders; }

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}