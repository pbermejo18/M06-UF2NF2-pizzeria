package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @Column(name = "idpizza")
    int idPizza;
    @Column(name = "name")
    String name;
    @Column(name = "price")
    float price;


    public Pizza(int idPizza, String name, float price) {
        super();
        this.idPizza = idPizza;
        this.name = name;
        this.price = price;
    }

    public Pizza() {
        super();
    }

    public void setIdPizza(int idPizza) { this.idPizza = idPizza; }
    public int getIdPizza() { return idPizza; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setPrice(float price) { this.price = price; }
    public float getPrice() { return price; }

    @Override
    public String toString() {
        return "Pizza{" +
                "idPizza=" + idPizza +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
