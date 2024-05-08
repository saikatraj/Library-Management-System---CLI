import java.util.HashSet;
import java.util.Set;

public class Librarian extends Person {
    private static final Set<Integer> ASSIGNED_STAFF_NUMBERS = new HashSet<>();
    private int staffNumber;

    public Librarian() {
        super();
        this.staffNumber = 0;
    }

    public Librarian(String firstName, String middleName, String lastName, int dob, int mob, int yob, String gender, int staffNumber) {
        super(firstName, middleName, lastName, dob, mob, yob, gender);
        setStaffNumber(staffNumber);
    }

    public Librarian(String firstName, String lastName, int dob, int mob, int yob, String gender, int staffNumber) {
        super(firstName, lastName, dob, mob, yob, gender);
        setStaffNumber(staffNumber);
    }

    public int getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber) {
        if (staffNumber <= 0) {
            throw new IllegalArgumentException("Invalid staff number");
        }

        synchronized (ASSIGNED_STAFF_NUMBERS) {
            if (ASSIGNED_STAFF_NUMBERS.contains(staffNumber)) {
                throw new IllegalArgumentException("Staff number already in use");
            }
            ASSIGNED_STAFF_NUMBERS.add(staffNumber);
        }

        this.staffNumber = staffNumber;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        Library.addBook(book);
    }

    public void removeBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        Library.removeBook(book);
    }

    public void registerLibraryUser(LibraryUser user) {
        if (user == null) {
            throw new IllegalArgumentException("Library user cannot be null");
        }
        Library.registerUser(user);
    }

    public void removeLibraryUser(LibraryUser user) {
        if (user == null) {
            throw new IllegalArgumentException("Library user cannot be null");
        }
        Library.removeUser(user);
    }
}
