import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;
    private LibraryUser borrowedBy;
    private LocalDate borrowedDate;

    public Book() {
        this("Unknown", "Unknown", "Unknown");
    }

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
        this.borrowedBy = null;
        this.borrowedDate = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public LibraryUser getBorrowedBy() {
        return borrowedBy;
    }

    public LocalDate getBorrowedDate() {
        if (borrowedDate == null) {
            return null; // Handle when the book is not yet borrowed
        }
        return borrowedDate;
    }

    public void borrow(LibraryUser user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (isBorrowed) {
            throw new IllegalStateException("Book is already borrowed");
        }

        isBorrowed = true;
        borrowedBy = user;
        borrowedDate = LocalDate.now();
    }

    public void returnBook() {
        if (!isBorrowed) {
            throw new IllegalStateException("Book is not currently borrowed");
        }

        isBorrowed = false;
        borrowedBy = null;
        borrowedDate = null;
    }

    public LocalDate getDueDate() {
        if (borrowedDate == null) {
            throw new IllegalStateException("Book has not been borrowed");
        }
        return borrowedDate.plusDays(14);
    }
}
