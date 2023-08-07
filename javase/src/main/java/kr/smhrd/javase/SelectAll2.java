package kr.smhrd.javase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;
import kr.smhrd.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SelectAll2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
        EntityManager em = emf.createEntityManager();

        // Fetch all products from the database using JPA with JPQL
        String jpql = "SELECT p FROM Product p"; // JPQL query
        List<Product> productList = em.createQuery(jpql, Product.class)
                                     .getResultList();

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
