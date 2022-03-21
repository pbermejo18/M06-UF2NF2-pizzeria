import Entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

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
        //MP.deleteIngredients(1);

        fa.readPizzasFile("pizzas.txt");
        for (int i = 0; i < fa.llistaPizzes.size(); i++) {
            System.out.println(fa.llistaPizzes.get(i).toString());
            MP.addPizzas(fa.llistaPizzes.get(i));
        }
        fa.printPizzas();
        MP.updatePizzas(7,"Mediterrania", 12.05f);

        fa.readContainsFile("contains.txt");
        for (int i = 0; i < fa.llistaContains.size(); i++) {
            System.out.println(fa.llistaContains.get(i).toString());
            MP.addContains(fa.llistaContains.get(i));
        }
        fa.printContains();
        MP.updateContains(fa.llistaIngredients.get(0),fa.llistaPizzes.get(0),fa.llistaIngredients.get(4),fa.llistaPizzes.get(5));
        MP.deleteContains(fa.llistaIngredients.get(0),fa.llistaPizzes.get(0));
        MP.deleteIngredients(4);
        MP.deletePizzas(4);

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
        MP.updateCustomer(6,"Jose","C/Prat","jose@gmail.com","670899478");
        MP.deleteCustomer(3);
        //MP.updateOrder(7,"2010-06-13",4);
        MP.deleteOrder(9);

        MP.deleteOrdersDetails(fa.llistaPizzes.get(0), fa.llistaCustomers.get(fa.llistaCustomers.size()-1).getOrder(0));
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
    public void updatePizzas(Integer PizzaID, String name, float price) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pizza pizza = (Pizza) em.find(Pizza.class, PizzaID);
        pizza.setName(name);
        pizza.setPrice(price);
        em.merge(pizza);
        em.getTransaction().commit();
        em.close();
    }
    public void deletePizzas(Integer PizzaID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Pizza pizza = (Pizza) em.find(Pizza.class, PizzaID);
        em.remove(pizza);
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
    public void updateContains(Ingredient ingredient, Pizza pizza, Ingredient ingredient2, Pizza pizza2) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Contains contains = (Contains) em.find(Contains.class, new ContainsId(ingredient.getIdIngredient(), pizza.getIdPizza()));
        contains.setIngredient(ingredient2);
        contains.setPizza(pizza2);
        em.merge(contains);
        em.getTransaction().commit();
        em.close();
    }
    public void deleteContains(Ingredient ingredient, Pizza pizza) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Contains contains = (Contains) em.find(Contains.class, new ContainsId(ingredient.getIdIngredient(), pizza.getIdPizza()));
        em.remove(contains);
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
    public void updateCustomer(Integer CustomerID, String name, String address, String email, String phone) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer customer = (Customer) em.find(Customer.class, CustomerID);
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setPhone(phone);
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
    }
    public void deleteCustomer(Integer CustomerID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Customer customer = (Customer) em.find(Customer.class, CustomerID);
        em.remove(customer);
        em.getTransaction().commit();
        em.close();
    }

    public void updateOrder(Integer OrderID, Date orderdate, Customer idcustomer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Order order = (Order) em.find(Order.class, OrderID);
        order.setOrderDate(orderdate);
        order.setIdCustomer(idcustomer);
        em.merge(order);
        em.getTransaction().commit();
        em.close();
    }
    public void deleteOrder(Integer OrderID) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Order order = (Order) em.find(Order.class, OrderID);
        em.remove(order);
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
    public void updateOrdersDetails(Integer OrdersDetailID, Date orderdate, Customer idcustomer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        OrdersDetail ordersDetail = (OrdersDetail) em.find(OrdersDetail.class, OrdersDetailID);
        //ordersDetail.setOrderDate(orderdate);
        //ordersDetail.setIdCustomer(idcustomer);
        em.merge(ordersDetail);
        em.getTransaction().commit();
        em.close();
    }
    public void deleteOrdersDetails(Pizza pizza,Order order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        OrdersDetail ordersDetail = (OrdersDetail) em.find(OrdersDetail.class, new OrdersDetailsId(pizza.getIdPizza(), order.getIdOrder()));
        em.remove(ordersDetail);
        em.getTransaction().commit();
        em.close();
    }
}