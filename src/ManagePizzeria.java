import Entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.text.ParseException;

public class ManagePizzeria {
    private static EntityManagerFactory emf;

    public static void main(String[] args) throws IOException, ParseException {
        try {
            emf = Persistence.createEntityManagerFactory("MagazineJPA");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object."
                    + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManagePizzeria MP = new ManagePizzeria();
        FileAccessor fa = new FileAccessor();

        fa.readIngredientsFile("ingredients.txt");
        for (int i = 0; i < fa.llistaIngredients.size(); i++) {
            System.out.println(fa.llistaIngredients.get(i).toString());
            MP.addIngredients(fa.llistaIngredients.get(i));
        }
        MP.updateIngredients(9,"Queso de cabra");
        MP.deleteIngredients(1);

        fa.readPizzasFile("pizzas.txt");
        for (int i = 0; i < fa.llistaPizzes.size(); i++) {
            System.out.println(fa.llistaPizzes.get(i).toString());
            MP.addPizzas(fa.llistaPizzes.get(i));
        }
        fa.printPizzas();

        fa.readContainsFile("contains.txt");
        for (int i = 0; i < fa.llistaContains.size(); i++) {
            System.out.println(fa.llistaContains.get(i).toString());
            MP.addContains(fa.llistaContains.get(i));
        }
        fa.printContains();

        // Leer y printar customers + orders + ordersdetails
        fa.readCustomersFile("customers.txt");
        fa.readOrdersFile("orders.txt");
        fa.readOrdersDetailsFile("ordersdetails.txt");
        System.out.println("Customers llegits des del fitxer");
        for (int i = 0; i < fa.llistaCustomers.size(); i++) {
            System.out.println(fa.llistaCustomers.get(i).toString());
            MP.addCustomer(fa.llistaCustomers.get(i));
        }
        for (int i = 0; i < fa.llistaCustomers.size(); i++) {
            for (int j = 0; j < fa.llistaCustomers.get(i).getOrders().size(); j++) {
                for (int k = 0; k < fa.llistaCustomers.get(i).getOrder(j).getOrdersdetails().size(); k++) {
                    MP.addOrdersDetails(fa.llistaCustomers.get(i).getOrder(j).getOrderDetail(k));
                }
            }
        }

        fa.printCustomers();

    }

    public void addIngredients(Ingredient ingredient) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ingredient);
        em.getTransaction().commit();
        em.close();
    }
    public void updateIngredients(Integer IngredientID, String name) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Ingredient ingredient = (Ingredient) em.find(Ingredient.class, IngredientID);
        ingredient.setName(name);
        em.merge(ingredient);
        em.getTransaction().commit();
        em.close();
    }
    public void deleteIngredients(Integer IngredientID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Ingredient ingredient = (Ingredient) em.find(Ingredient.class, IngredientID);
        em.remove(ingredient);
        em.getTransaction().commit();
        em.close();
    }

    public void addPizzas(Pizza pizza) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(pizza);
        em.getTransaction().commit();
        em.close();
    }

    public void addContains(Contains contains) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(contains);
        em.getTransaction().commit();
        em.close();
    }
    public void addCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    public void addOrdersDetails(OrdersDetail ordersDetail) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ordersDetail);
        em.getTransaction().commit();
        em.close();
    }


}
