package example;

import example.price.ChildrenPrice;
import example.price.HorrorPrice;
import example.price.NewReleasePrice;
import example.price.RegularPrice;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void testGetStatementWithSingleRegularMovie() {
        Movie regularMovie = new Movie("The Godfather", new RegularPrice());
        Rental rental = new Rental(regularMovie, 3); // 3 days rented
        Customer customer = new Customer("John Doe", List.of(rental));

        String expected = """
                Rental Record for John Doe
                \tThe Godfather\t3.5
                Amount owed is 3.5
                You earned 1 frequent renter points""";

        assertEquals(expected, customer.getStatement());
    }

    @Test
    public void testGetStatementWithSingleNewReleaseMovie() {
        Movie newRelease = new Movie("Oppenheimer", new NewReleasePrice());
        Rental rental = new Rental(newRelease, 2); // 2 days rented
        Customer customer = new Customer("Alice", List.of(rental));

        String expected = """
                Rental Record for Alice
                \tOppenheimer\t6.0
                Amount owed is 6.0
                You earned 2 frequent renter points""";

        assertEquals(expected, customer.getStatement());
    }

    @Test
    public void testGetStatementWithSingleChildrensMovie() {
        Movie childrensMovie = new Movie("Frozen", new ChildrenPrice());
        Rental rental = new Rental(childrensMovie, 4); // 4 days rented
        Customer customer = new Customer("Bob", List.of(rental));

        String expected = """
                Rental Record for Bob
                \tFrozen\t3.0
                Amount owed is 3.0
                You earned 1 frequent renter points""";

        assertEquals(expected, customer.getStatement());
    }

    @Test
    public void testGetStatementWithMultipleRentals() {
        Movie regularMovie = new Movie("The Godfather", new RegularPrice());
        Movie newRelease = new Movie("Oppenheimer", new NewReleasePrice());
        Movie childrensMovie = new Movie("Frozen", new ChildrenPrice());

        Rental rental1 = new Rental(regularMovie, 3);
        Rental rental2 = new Rental(newRelease, 1);
        Rental rental3 = new Rental(childrensMovie, 2);

        Customer customer = new Customer("John Doe", List.of(rental1, rental2, rental3));

        String expected = """
                Rental Record for John Doe
                \tThe Godfather\t3.5
                \tOppenheimer\t3.0
                \tFrozen\t1.5
                Amount owed is 8.0
                You earned 3 frequent renter points""";

        assertEquals(expected, customer.getStatement());
    }

    @Test
    public void testGetStatementWithEmptyRentals() {
        Customer customer = new Customer("John Doe", List.of());

        String expected = """
                Rental Record for John Doe
                Amount owed is 0.0
                You earned 0 frequent renter points""";

        assertEquals(expected, customer.getStatement());
    }

    @Test
    public void testFrequentRenterPointsForNewReleaseMoreThanOneDay() {
        Movie newRelease = new Movie("Oppenheimer", new NewReleasePrice());
        Rental rental = new Rental(newRelease, 3); // 3 days rented
        Customer customer = new Customer("Alice", List.of(rental));

        String expected = """
                Rental Record for Alice
                \tOppenheimer\t9.0
                Amount owed is 9.0
                You earned 2 frequent renter points""";

        assertEquals(expected, customer.getStatement());
    }

    @Test
    public void testFrequentRenterPointsForSingleNewReleaseOneDay() {
        Movie newRelease = new Movie("Barbie", new NewReleasePrice());
        Rental rental = new Rental(newRelease, 1); // 1 day rented
        Customer customer = new Customer("Charlie", List.of(rental));

        String expected = """
                Rental Record for Charlie
                \tBarbie\t3.0
                Amount owed is 3.0
                You earned 1 frequent renter points""";

        assertEquals(expected, customer.getStatement());
    }

    @Test
    public void testGetStatementWithSingleHorrorMovie() {
        Movie horrorMovie = new Movie("Alien", new HorrorPrice());
        Rental rental = new Rental(horrorMovie, 8); // 3 days rented
        Customer customer = new Customer("John Doe", List.of(rental));

        String expected = """
                Rental Record for John Doe
                \tAlien\t2.0
                Amount owed is 2.0
                You earned 2 frequent renter points""";

        assertEquals(expected, customer.getStatement());
    }
}