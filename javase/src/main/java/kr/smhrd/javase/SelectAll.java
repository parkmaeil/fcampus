package kr.smhrd.javase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;
import kr.smhrd.model.*;

public class SelectAll {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
        EntityManager em = emf.createEntityManager();

        // Fetch all products from the database using Named Query
        TypedQuery<Product> query = em.createNamedQuery("Product.findAll", Product.class);
        List<Product> productList = query.getResultList();

        System.out.println("Printing all products...");
        for (Product product : productList) {
            System.out.println("Product ID: " + product.getId());
            System.out.println("Product Name: " + product.getName());
            System.out.println("Product Price: " + product.getPrice());
            System.out.println("-----------------------------");
        }

        em.close();
        emf.close();
    }
}
