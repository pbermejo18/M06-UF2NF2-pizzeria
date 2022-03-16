import Entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class FileAccessor {

    ArrayList<Customer> llistaCustomers = new ArrayList();
    ArrayList<Pizza> llistaPizzes = new ArrayList();
    ArrayList<Ingredient> llistaIngredients = new ArrayList();
    ArrayList<Contains> llistaContains = new ArrayList();

    public FileAccessor() {
    }

    public void readCustomersFile(String filename) throws IOException {
        int idcustomer;
        String name, address, email, phone;

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            idcustomer = Integer.parseInt(str.nextToken());
            name = str.nextToken();
            address = str.nextToken();
            email = str.nextToken();
            phone = str.nextToken();
            // System.out.println(id + name + country + year + active);
            llistaCustomers.add(new Customer(idcustomer, name, address, email, phone));
        }
        br.close();
    }
    public void printCustomers() {
        for (int i = 0; i < llistaCustomers.size(); i++) {
            System.out.println(llistaCustomers.get(i).toString());
            for (int j = 0; j < llistaCustomers.get(i).getOrders().size(); j++) {
                System.out.println("    " + llistaCustomers.get(i).getOrder(j));
                for (int k = 0; k < llistaCustomers.get(i).getOrder(j).getOrdersdetails().size(); k++) {
                    System.out.println("        " + llistaCustomers.get(i).getOrder(j).getOrderDetail(k).toString());
                }
            }
        }
    }

    public void readPizzasFile(String filename) throws IOException {
        int idpizza;
        String name;
        float price;

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            idpizza = Integer.parseInt(str.nextToken());
            name = str.nextToken();
            price = Float.parseFloat(str.nextToken());
            // System.out.println(id + name + country + year + active);
            llistaPizzes.add(new Pizza(idpizza, name, price));
        }
        br.close();
    }
    public void printPizzas() {
        for (int i = 0; i < llistaPizzes.size(); i++) {
            System.out.println(llistaPizzes.get(i).toString());
        }
    }

    public ArrayList<Customer> readOrdersFile(String filename) throws IOException, ParseException {
        int idorder, idcustomer;
        Date orderdate;
        DateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD");

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            idorder = Integer.parseInt(str.nextToken());
            orderdate = dateformat.parse(str.nextToken());

            idcustomer = Integer.parseInt(str.nextToken());

            Order o = new Order(idorder, orderdate, llistaCustomers.get(idcustomer-1));
            llistaCustomers.get(idcustomer-1).addOrder(o);
        }
        br.close();
        return llistaCustomers;
    }

    public ArrayList<Customer> readOrdersDetailsFile(String filename) throws IOException, ParseException {
        int quantity, idorder;
        float priceeach;
        int idpizza;

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            quantity = Integer.parseInt(str.nextToken());
            priceeach = Float.parseFloat(str.nextToken());
            idpizza = Integer.parseInt(str.nextToken());
            idorder = Integer.parseInt(str.nextToken());

            OrdersDetail od = new OrdersDetail(quantity, priceeach, llistaPizzes.get(idpizza-1),llistaCustomers.get(idorder - 1));
            // llistaCustomers.get(idcustomer-1).addOrder(o);
            for (int i = 0; i < llistaCustomers.size(); i++) {
                for (int j = 0; j < llistaCustomers.get(i).getOrders().size(); j++)
                    if(llistaCustomers.get(i).getOrder(j).getIdOrder() == idorder)
                        llistaCustomers.get(i).getOrder(j).getOrdersdetails().add(od);
            }
        }
        br.close();
        return llistaCustomers;
    }

    public void readIngredientsFile(String filename) throws IOException, ParseException {
        int idingredient;
        String name;

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            idingredient = Integer.parseInt(str.nextToken());
            name = str.nextToken();


            llistaIngredients.add(new Ingredient(idingredient, name));
        }
        br.close();
    }

    public void printIngredients() {
        for (int i = 0; i < llistaIngredients.size(); i++) {
            System.out.println(llistaIngredients.get(i).toString());
        }
    }

    public void readContainsFile(String filename) throws IOException, ParseException {
        int ingredient, pizza;

        BufferedReader br = new BufferedReader(new FileReader(filename));
        String linea = "";
        while ((linea = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linea, ",");
            ingredient = Integer.parseInt(str.nextToken());
            pizza = Integer.parseInt(str.nextToken());


            llistaContains.add(new Contains(llistaIngredients.get(ingredient-1), llistaPizzes.get(pizza-1)));
        }
        br.close();
    }
    public void printContains() {
        for (int i = 0; i < llistaContains.size(); i++) {
            System.out.println(llistaContains.get(i).toString());
        }
    }
}