import org.junit.Before;
import org.junit.Test;
import pack.BuddyInfo;

import javax.persistence.*;
import java.util.List;

import static org.junit.Assert.*;

public class BuddyInfoTest {

    private BuddyInfo Vijay;

    @Before
    public void setUp() throws Exception {
        Vijay = new BuddyInfo("Vijay", "1375 Heron Rd.", "611");
    }



    @Test
    public void testGetName(){
        assertEquals("Name should be Vijay", "Vijay", Vijay.getName());
    }

    @Test
    public void testSetName(){
        Vijay.setName("VJ");
        assertEquals("Name should be changed from Vijay to VJ", "VJ", Vijay.getName());
    }



    @Test
    public void testGetNum(){
        assertEquals("Number should be 611", "611", Vijay.getNum());
    }

    @Test
    public void testSetNum(){
        Vijay.setNum("613-620-5650");
        assertEquals("Number should be changed from 611 to 613-620-5650", "613-620-5650", Vijay.getNum());
    }



    @Test
    public void testGetAdd(){
        assertEquals("Address should be 1375 Heron Road", "1375 Heron Rd.", Vijay.getAddr());
    }

    @Test
    public void testSetAdd(){
        Vijay.setAddr("1234 Apple Ave.");
        assertEquals("Address should be changed from 1375 Heron Road to 1234 Apple Ave.", "1234 Apple Ave.", Vijay.getAddr());
    }

    @Test
    public void performJPA(){
        //Create objects represent some buddies
        BuddyInfo duncan = new BuddyInfo("Duncan", "1234 Potato Ave.", "911");

        BuddyInfo vijay = new BuddyInfo("Vijay", "1375 Heron Rd.", "611");

        BuddyInfo bob = new BuddyInfo();
        bob.setAddr("1212 Plain St.");
        bob.setNum("1234567890");

        // Connect to db through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");

        EntityManager em = emf.createEntityManager();

        // Create a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persist the pack.BuddyInfo objects
        em.persist(duncan);
        em.persist(vijay);
        em.persist(bob);

        tx. commit();

        // Query db contents using JPQL query
        Query q = em.createQuery("SELECT b FROM BuddyInfo b");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> results = q.getResultList();

        System.out.println("List of products\n---------------");

        for (BuddyInfo b : results) {
            System.out.println(b + "\n(Primary Key = " + b.getName() + ")");
        }

        em.close();
        emf.close();
    }

}