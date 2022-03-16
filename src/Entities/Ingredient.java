package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @Column(name = "idingredient")
    int idIngredient;
    @Column(name = "name")
    String name;

    public Ingredient(int idIngredient, String name) {
        super();
        this.idIngredient = idIngredient;
        this.name = name;

    }

    public Ingredient() {
        super();
    }

    public void setIdIngredient(int idIngredient) { this.idIngredient = idIngredient; }
    public int getIdIngredient() { return idIngredient; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient=" + idIngredient +
                ", name='" + name + '\'' +
                '}';
    }
}
