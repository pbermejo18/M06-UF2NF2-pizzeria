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