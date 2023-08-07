package kr.smhrd.javase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import kr.smhrd.model.*;

public class InsertApp {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
        EntityManager em = emf.createEntityManager();
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();

        scanner.nextLine(); // Consume the remaining newline character after reading the double.

        // Persist the data using JPA
        em.getTransaction().begin();
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        em.persist(product);
        em.getTransaction().commit();

        System.out.println("\nProcessing product information...");
        System.out.println("Product ID: " + product.getId());
        System.out.println("Product Name: " + product.getName());
        System.out.println("Product Price: " + product.getPrice());

        em.close();
        emf.close();
    }
}
