package edu.eci.cvds.tdd.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;


public class LibraryTest {

    private Library library;

    @Before
    public void setUp(){
        library = new Library();
    }

    @Test
    public void testAddNewBook() {
        Book book = new Book("Libro1", "Jeisson", "123");
        assertTrue(library.addBook(book)); 
        assertTrue(library.isBookAvailable("123"));
        
    }

    @Test
    public void testAddExistingBookIncreasesCount() {
        Book book = new Book("Libro1", "Angel", "123");
        library.addBook(book);
        library.addBook(book);
        assertTrue(library.isBookAvailable("123"));
    }

    @Test
    public void testAddNullBookReturnsFalse() {
        assertFalse(library.addBook(null)); 
    }

    @Test
    public void testLoanSuccessful() {
        Book book = new Book("Libro1", "Jeisson", "123");
        User user = new User("Angel Cuervo","01");
        library.addBook(book);
        library.addUser(user);
        Loan loan = library.loanABook("01", "123");
        assertNotNull(loan); 
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
    }

    @Test
    public void testLoanFailsBookUnavailable() {
        User user = new User("Angel Cuervo","01");
        library.addUser(user);
        Loan loan = library.loanABook("01", "123");
        assertNull(loan); 
    }

    @Test
    public void testLoanFailsUserDoesNotExist() {
        Book book = new Book("Libro1", "Jeisson", "123");
        library.addBook(book);
        Loan loan = library.loanABook("002", "123");
        assertNull(loan); 
    }

    @Test
    public void testLoanFailsDuplicateLoan() {
        Book book = new Book("Libro1", "Jeisson", "123");
        User user = new User("Angel Cuervo","01");
        library.addBook(book);
        library.addUser(user);
        library.loanABook("01", "123");
        Loan loan = library.loanABook("01", "123");
        assertNull(loan); 
    }




    
    
}
