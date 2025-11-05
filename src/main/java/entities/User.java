package entities;

import java.util.Objects;

public class User {
    private final String firstName;
    private final String lastName;
    private final Role role;
    private int id;

    public User(String firstName, String lastName, Role role) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(int id, String firstName, String lastName, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }
}
