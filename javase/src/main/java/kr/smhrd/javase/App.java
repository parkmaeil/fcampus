package kr.smhrd.javase;

import javax.persistence.*;
import kr.smhrd.model.*;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("YourPersistenceUnitName");
        EntityManager em = emf.createEntityManager();

        // Insert a new entity
        /*
        em.getTransaction().begin();
        Product product = new Product();
        product.setName("마우스");
        product.setPrice(19.99);
        em.persist(product);
        em.getTransaction().commit();
        */
        // Retrieve an entity by ID
        em.getTransaction().begin();
        Product retrievedProduct = em.find(Product.class, 4L);
        System.out.println(retrievedProduct);
        em.getTransaction().commit();
        

        em.close();
        emf.close();
    }
}
