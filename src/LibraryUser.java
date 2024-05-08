import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LibraryUser extends Person {
    private static final Set<Integer> ASSIGNED_CARD_NUMBERS = new HashSet<>();
    private int libraryCardNumber;
    private List<Book> borrowedBooks;

    public LibraryUser() {
        super();
        this.borrowedBooks = new ArrayList<>();
    }

    public LibraryUser(String firstName, String middleName, String lastName, int dob, int mob, int yob, String gender) {
        super(firstName, middleName, lastName, dob, mob, yob, gender);
        this.borrowedBooks = new ArrayList<>();
    }

    public LibraryUser(String firstName, String lastName, int dob, int mob, int yob, String gender) {
        super(firstName, lastName, dob, mob, yob, gender);
        this.borrowedBooks = new ArrayList<>();
    }

    public void setLibraryCardNumber(int libraryCardNumber) {
        if (libraryCardNumber <= 0) {
            throw new IllegalArgumentException("Invalid library card number");
        }

        synchronized (ASSIGNED_CARD_NUMBERS) {
            if (ASSIGNED_CARD_NUMBERS.contains(libraryCardNumber)) {
                throw new IllegalArgumentException("Library card number already in use");
            }
            ASSIGNED_CARD_NUMBERS.add(libraryCardNumber);
        }

        this.libraryCardNumber = libraryCardNumber;
    }

    public int getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks); // Returning an unmodifiable list
    }

    public void borrowBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        if (borrowedBooks.contains(book)) {
            throw new IllegalArgumentException("Book is already borrowed");
        }
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            throw new IllegalArgumentException("This book was not borrowed");
        }
        borrowedBooks.remove(book);
    }
}
