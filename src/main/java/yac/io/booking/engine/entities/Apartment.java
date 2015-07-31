package yac.io.booking.engine.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by geoffroy on 18/07/15.
 */
@Entity
@Table(name = "apartment_apartments")
public class Apartment {
    private Long id;
    private String name;
    private int capacity;
    private List<Booking> bookings;

    public Apartment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", insertable = true, updatable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "capacity", insertable = true, updatable = false)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @OneToMany(mappedBy = "apartment")
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private Apartment apartment = new Apartment();

        public Builder id(Long id) {
            apartment.id = id;
            return this;
        }

        public Builder name(String name) {
            apartment.name = name;
            return this;
        }

        public Builder capacity(int capacity) {
            apartment.capacity = capacity;
            return this;
        }

        public Builder bookings(List<Booking> bookings) {
            apartment.bookings = bookings;
            return this;
        }

        public Apartment build() {
            return apartment;
        }
    }
}
