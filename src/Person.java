import java.time.Year;

public class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private int dob;
    private int mob;
    private int yob;
    private String gender;

    public Person() {
        this("Unknown", "Unknown", "Unknown", 0, 0, 0, "Unknown");
    }

    public Person(String firstName, String lastName, int dob, int mob, int yob, String gender) {
        setFirstName(firstName);
        setLastName(lastName);
        setDob(dob);
        setMob(mob);
        setYob(yob);
        setGender(gender);
        this.middleName = "Unknown";
    }

    public Person(String firstName, String middleName, String lastName, int dob, int mob, int yob, String gender) {
        this(firstName, lastName, dob, mob, yob, gender);
        setMiddleName(middleName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDob() {
        return dob;
    }

    public int getMob() {
        return mob;
    }

    public int getYob() {
        return yob;
    }

    public String getGender() {
        return gender;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        if (middleName == null) {
            this.middleName = "Unknown";
        } else {
            this.middleName = middleName.trim().isEmpty() ? "Unknown" : middleName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName;
    }

    public void setDob(int dob) {
        if (dob < 1 || dob > 31) {
            throw new IllegalArgumentException("Invalid day of birth");
        }
        this.dob = dob;
    }

    public void setMob(int mob) {
        if (mob < 1 || mob > 12) {
            throw new IllegalArgumentException("Invalid month of birth");
        }
        this.mob = mob;
    }

    public void setYob(int yob) {
        int currentYear = Year.now().getValue();
        if (yob < 1900 || yob > currentYear) {
            throw new IllegalArgumentException("Invalid year of birth");
        }
        this.yob = yob;
    }

    public void setGender(String gender) {
        if (gender == null || (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F"))) {
            throw new IllegalArgumentException("Invalid gender. Use 'M' for male or 'F' for female.");
        }
        this.gender = gender;
    }

    public int getAge() {
        if (yob == 0) {
            throw new IllegalStateException("Year of birth is unknown");
        }

        int currentYear = Year.now().getValue();
        int age = currentYear - yob;

        if (mob > Year.now().getValue() || (mob == Year.now().getValue() && dob > Year.now().getValue())) {
            age--; // If the birthday hasn't occurred yet this year
        }

        return age;
    }

    public String getName() {
        if (middleName.equalsIgnoreCase("Unknown")) {
            return firstName + " " + lastName;
        } else {
            return firstName + " " + middleName + " " + lastName;
        }
    }
}
