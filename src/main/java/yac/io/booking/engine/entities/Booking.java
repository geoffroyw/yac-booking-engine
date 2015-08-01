package yac.io.booking.engine.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geoffroy on 18/07/15.
 */
@Entity
@Table(name = "rentals")
public class Booking extends AuditableEntity {


    public enum Status {
        draft, confirmed, canceled
    }

    private Long id;

    private Customer customer;

    private Apartment apartment;

    private Date startDate;

    private Date endDate;

    private Status state;

    private int numberOfAdult;

    private int numberOfChildren;


    public Booking() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", nullable = false)
    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    public Status getState() {
        return state;
    }

    public void setState(Status state) {
        this.state = state;
    }

    @Column(name = "number_of_adult")
    public int getNumberOfAdult() {
        return numberOfAdult;
    }

    public void setNumberOfAdult(int numberOfAdult) {
        this.numberOfAdult = numberOfAdult;
    }

    @Column(name = "number_of_children")
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customer=" + customer +
                ", apartment=" + apartment +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", state=" + state +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Booking booking = new Booking();
        private Long id;
        private Customer customer;
        private Apartment apartment;
        private Date startDate;
        private Date endDate;
        private Status status;

        public Builder id(Long id) {
            booking.id = id;
            return this;
        }

        public Builder customer(Customer customer) {
            booking.customer = customer;
            return this;
        }

        public Builder apartment(Apartment apartment) {
            booking.apartment = apartment;
            return this;
        }

        public Builder startDate(Date startDate) {
            booking.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            booking.endDate = endDate;
            return this;
        }

        public Builder status(Status status) {
            booking.state = status;
            return this;
        }

        public Builder numberOfAdult(int nb) {
            booking.numberOfAdult = nb;
            return this;
        }

        public Builder numberOfChildren(int nb) {
            booking.numberOfChildren = nb;
            return this;
        }

        public Booking build() {
            return booking;
        }
    }
}
