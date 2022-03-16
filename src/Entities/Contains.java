package Entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "contains")
public class Contains {
    @EmbeddedId
    private ContainsId containsId;

    @Transient
    Ingredient ingredient;
    @Transient
    Pizza pizza;

    public Contains(Ingredient ingredient, Pizza pizza) {
        super();
        this.ingredient = ingredient;
        this.pizza = pizza;
        containsId = new ContainsId(ingredient.getIdIngredient(), pizza.getIdPizza());
    }

    public Contains() {
        super();
    }

    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; this.containsId.setIdingredient(ingredient.getIdIngredient()); }
    public Ingredient getIngredient() { return ingredient; }

    public void setPizza(Pizza pizza) { this.pizza = pizza; this.containsId.setIdpizza(pizza.getIdPizza()); }
    public Pizza getPizza() { return pizza; }

    @Override
    public String toString() {
        return "Contains{" +
                "ingredient=" + ingredient +
                ", pizza=" + pizza +
                '}';
    }


}

@Embeddable
class ContainsId implements Serializable {
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