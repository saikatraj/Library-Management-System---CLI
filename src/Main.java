import java.util.Scanner;
import java.util.List;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a librarian and some library users for the demonstration
        Librarian librarian = new Librarian("John", "Doe", 15, 6, 1975, "M", 101);
        LibraryUser user1 = new LibraryUser("Alice", "Smith", 20, 8, 1990, "F");
        LibraryUser user2 = new LibraryUser("Bob", "Johnson", 5, 12, 1985, "M");

        user1.setLibraryCardNumber(1001);
        user2.setLibraryCardNumber(1002);

        // Register the users and add a librarian to a simple user list
        Library.registerUser(user1);
        Library.registerUser(user2);

        // Add a few books to the library
        Book book1 = new Book("1984", "George Orwell", "9780451524935");
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084");
        librarian.addBook(book1);
        librarian.addBook(book2);

        while (true) {
            System.out.println("Select User Type:");
            System.out.println("1. Librarian");
            System.out.println("2. Library User");
            System.out.println("3. Exit");

            int userType = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (userType == 1) {
                System.out.print("Enter staff number to log in: ");
                int staffNumber = scanner.nextInt();
                scanner.nextLine();

                if (staffNumber == librarian.getStaffNumber()) {
                    librarianMenu(librarian);
                } else {
                    System.out.println("Invalid staff number.");
                }
            } else if (userType == 2) {
                System.out.print("Enter library card number to log in: ");
                int libraryCardNumber = scanner.nextInt();
                scanner.nextLine();

                LibraryUser loggedInUser = getLibraryUserByCardNumber(libraryCardNumber);

                if (loggedInUser != null) {
                    libraryUserMenu(loggedInUser);
                } else {
                    System.out.println("Invalid library card number.");
                }
            } else if (userType == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        }

        scanner.close(); // Always remember to close the scanner
    }

    private static LibraryUser getLibraryUserByCardNumber(int cardNumber) {
        for (LibraryUser user : Library.getUsers()) {
            if (user.getLibraryCardNumber() == cardNumber) {
                return user;
            }
        }
        return null;
    }

    private static void librarianMenu(Librarian librarian) {
        while (true) {
            System.out.println("Librarian Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Register User");
            System.out.println("4. Remove User");
            System.out.println("5. List Available Books");
            System.out.println("6. List Borrowed Books");
            System.out.println("7. Save to File");
            System.out.println("8. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addBook(librarian);
                    break;
                case 2:
                    removeBook(librarian);
                    break;
                case 3:
                    registerUser(librarian);
                    break;
                case 4:
                    removeUser(librarian);
                    break;
                case 5:
                    listAvailableBooks();
                    break;
                case 6:
                    listBorrowedBooks();
                    break;
                case 7:
                    saveToFile();
                    break;
                case 8:
                    return; // Logout
                default:
                    System.out.println("Invalid choice. Please select 1 to 8.");
                    break;
            }
        }
    }

    private static void addBook(Librarian librarian) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Book newBook = new Book(title, author, isbn);
        librarian.addBook(newBook);

        System.out.println("Book added successfully!");
    }

    private static void removeBook(Librarian librarian) {
        System.out.print("Enter book ISBN to remove: ");
        String isbn = scanner.nextLine();

        Book bookToRemove = Library.findBookByIsbn(isbn);

        if (bookToRemove != null) {
            librarian.removeBook(bookToRemove);
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book with given ISBN not found.");
        }
    }

    private static void registerUser(Librarian librarian) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter day of birth: ");
        int dob = scanner.nextInt();
        System.out.print("Enter month of birth: ");
        int mob = scanner.nextInt();
        System.out.print("Enter year of birth: ");
        int yob = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter gender (M/F): ");
        String gender = scanner.nextLine();
        System.out.print("Enter library card number: ");
        int cardNumber = scanner.nextInt();

        LibraryUser newUser = new LibraryUser(firstName, lastName, dob, mob, yob, gender);
        newUser.setLibraryCardNumber(cardNumber);

        librarian.registerLibraryUser(newUser);

        System.out.println("User registered successfully!");
    }

    private static void removeUser(Librarian librarian) {
        System.out.print("Enter library card number of user to remove: ");
        int cardNumber = scanner.nextInt();

        LibraryUser userToRemove = getLibraryUserByCardNumber(cardNumber);

        if (userToRemove != null) {
            librarian.removeLibraryUser(userToRemove);
            System.out.println("User removed successfully!");
        } else {
            System.out.println("User with given library card number not found.");
        }
    }

    private static void listAvailableBooks() {
        System.out.println("Available Books:");
        List<Book> availableBooks = Library.listAvailableBooks();
        for (Book book : availableBooks) {
            System.out.println(book.getTitle());
        }
    }

    private static void listBorrowedBooks() {
        System.out.println("Borrowed Books:");
        List<Book> borrowedBooks = Library.listBorrowedBooks();
        for (Book book : borrowedBooks) {
            System.out.println(book.getTitle() + " borrowed by " + book.getBorrowedBy().getName() + " on " + book.getBorrowedDate());
        }
    }

    private static void saveToFile() {
        System.out.print("Enter filename to save: ");
        String filename = scanner.nextLine();
        Library.saveToFile(filename);
    }

    private static void libraryUserMenu(LibraryUser user) {
        while (true) {
            System.out.println("Library User Menu:");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. List Available Books");
            System.out.println("4. List Borrowed Books");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    borrowBook(user);
                    break;
                case 2:
                    returnBook(user);
                    break;
                case 3:
                    listAvailableBooks();
                    break;
                case 4:
                    listBorrowedBooksByUser(user);
                    break;
                case 5:
                    return; // Logout
                default:
                    System.out.println("Invalid choice. Please select 1 to 5.");
                    break;
            }
        }
    }

    private static void borrowBook(LibraryUser user) {
        System.out.print("Enter book ISBN to borrow: ");
        String isbn = scanner.nextLine();

        Book bookToBorrow = Library.findBookByIsbn(isbn);

        if (bookToBorrow == null) {
            System.out.println("Book with given ISBN not found.");
            return;
        }

        if (bookToBorrow.isBorrowed()) {
            System.out.println("Book is already borrowed by " + bookToBorrow.getBorrowedBy().getName());
        } else {
            Library.borrowBook(user, bookToBorrow);
            System.out.println("Book borrowed successfully!");
        }
    }

    private static void returnBook(LibraryUser user) {
        System.out.print("Enter book ISBN to return: ");
        String isbn = scanner.nextLine();

        Book bookToReturn = Library.findBookByIsbn(isbn);

        if (bookToReturn == null) {
            System.out.println("Book with given ISBN not found.");
            return;
        }

        if (!bookToReturn.isBorrowed() || !bookToReturn.getBorrowedBy().equals(user)) {
            System.out.println("This book is not borrowed by you or not borrowed at all.");
        } else {
            Library.returnBook(user, bookToReturn);
            System.out.println("Book returned successfully!");
        }
    }

    private static void listBorrowedBooksByUser(LibraryUser user) {
        System.out.println("Borrowed Books:");
        List<Book> borrowedBooks = user.getBorrowedBooks();
        for (Book book : borrowedBooks) {
            System.out.println(book.getTitle() + " borrowed on " + book.getBorrowedDate());
        }
    }
}
