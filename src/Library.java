import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Library {
    // Using thread-safe collections to avoid race conditions in concurrent environments
    private static List<Book> books = new CopyOnWriteArrayList<>();
    private static List<LibraryUser> users = new CopyOnWriteArrayList<>();

    public static void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        books.add(book);
    }

    public static void removeBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        books.remove(book);
    }

    public static Book findBookByTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book findBookByIsbn(String isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN cannot be null");
        }
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public static void registerUser(LibraryUser user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        users.add(user);
    }

    public static void removeUser(LibraryUser user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        users.remove(user);
    }

    public static void borrowBook(LibraryUser user, Book book) {
        if (user == null || book == null) {
            throw new IllegalArgumentException("User and book cannot be null");
        }
        if (!users.contains(user)) {
            throw new IllegalStateException("User not registered in the library");
        }
        if (book.isBorrowed()) {
            throw new IllegalStateException("Book is already borrowed");
        }
        book.borrow(user);
        user.borrowBook(book);
    }

    public static void returnBook(LibraryUser user, Book book) {
        if (user == null || book == null) {
            throw new IllegalArgumentException("User and book cannot be null");
        }
        if (!book.isBorrowed()) {
            throw new IllegalStateException("Book is not currently borrowed");
        }
        book.returnBook();
        user.returnBook(book);
    }

    public static List<Book> listAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.isBorrowed()) {
                availableBooks.add(book);
            }
        }
        return Collections.unmodifiableList(availableBooks);
    }

    public static List<Book> listBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isBorrowed()) {
                borrowedBooks.add(book);
            }
        }
        return Collections.unmodifiableList(borrowedBooks);
    }

    public static List<LibraryUser> getUsers() {
        return Collections.unmodifiableList(users); // Return an unmodifiable view of the users list
    }
}
