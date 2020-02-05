import org.junit.Before;
import org.junit.Test;
import pack.AddressBook;
import pack.BuddyInfo;

import javax.persistence.*;
import java.util.List;

import static org.junit.Assert.*;

public class AddressBookTest {

    private AddressBook book;
    private BuddyInfo Duncan;
    private BuddyInfo Vijay;


    @Before
    public void setUp() throws Exception {
        book = new AddressBook();
        Duncan = new BuddyInfo("Duncan", "1234 Potato Ave.", "911");
        Vijay = new BuddyInfo("Vijay", "1375 Heron Rd.", "611");
        book.addBuddy(Duncan);

    }

    @Test
    public void testGet() {
        assertEquals("First item should be Duncan", Duncan, book.getBuddy(0));
    }

    @Test
    public void testAdd(){
        book.addBuddy(Vijay);
        assertEquals("Second item should be Vijay", Vijay, book.getBuddy(1));
    }

    @Test
    public void testRemove() {
        book.removeBuddy(0);
        assertNotEquals("Duncan should be removed from list", Duncan, book.getBuddy(0));
    }

    @Test
    public void performJPA(){
        //Create objects represent a pack.AddressBook
        // Why u do dis
        BuddyInfo bob = new BuddyInfo();
        bob.setAddr("1212 Plain St.");
        bob.setNum("1234567890");

        book.addBuddy(Vijay);
        book.addBuddy(bob);

        // Connect to db through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");

        EntityManager em = emf.createEntityManager();

        // Create a new transaction
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // Persist the pack.BuddyInfo objects
        em.persist(Duncan);
        em.persist(Vijay);
        em.persist(bob);

        em.persist(book);

        tx. commit();

        // Query db contents using JPQL query
        Query q = em.createQuery("SELECT a FROM AddressBook a");

        @SuppressWarnings("unchecked")
        List<AddressBook> results = q.getResultList();

        System.out.println("List of products\n---------------");

        for (AddressBook a: results) {
            System.out.println(a);
        }

        em.close();
        emf.close();
    }
}