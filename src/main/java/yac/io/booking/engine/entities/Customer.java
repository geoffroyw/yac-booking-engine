package yac.io.booking.engine.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by geoffroy on 18/07/15.
 */
@Entity
@Table(name = "customers")
public class Customer {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private List<Booking> bookings;

    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name", insertable = false, updatable = false, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", insertable = false, updatable = false, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email", insertable = false, updatable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone", insertable = false, updatable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @OneToMany(mappedBy = "customer")
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Customer customer = new Customer();

        public Builder id(Long id) {
            customer.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            customer.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            customer.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            customer.email = email;
            return this;
        }

        public Builder phone(String phone) {
            customer.phone = phone;
            return this;
        }

        public Builder bookings(List<Booking> bookings) {
            customer.bookings = bookings;
            return this;
        }

        public Customer build() {
            return customer;
        }
    }
}
