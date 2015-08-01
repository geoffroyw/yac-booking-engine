package yac.io.booking.engine.entities.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import yac.io.booking.engine.entities.Apartment;
import yac.io.booking.engine.entities.Booking;
import yac.io.booking.engine.entities.Customer;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by geoffroy on 31/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = RepositoryConfig.class)
public class BookingRepositoryTest {
    @Autowired
    ApartmentRepository apartmentRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BookingRepository repository;

    private Customer customer;
    private Apartment apartment;

    @Before
    public void setupMetaData() {
        apartment = apartmentRepository.save(Apartment.builder().capacity(3).name("name").build());
        customer = customerRepository.save(Customer.builder().firstName("firstName").lastName("lastName").build());
    }

    @Test
    public void testInsert() {
        Booking b = new Booking();
        b.setApartment(apartment);
        b.setCustomer(customer);

        b = repository.save(b);

        assertEquals(b, repository.findOne(b.getId()));
    }

    @Test
    public void number_of_booking_during_time_period_returns_1_if_queried_with_exact_same_date_as_an_existing_booking() {
        LocalDate from = LocalDate.of(2015, 7, 1);

        LocalDate to = LocalDate.of(2015, 7, 18);

        Date from_date = Date.from(from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date to_date = Date.from(to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Booking booking1 = Booking.builder().apartment(apartment).customer(customer)
                .startDate(from_date).endDate(to_date).build();

        repository.save(booking1);

        assertThat(repository.findNumberOfBookingDuringTimePeriod(apartment, from_date, to_date), is(1));

    }

    @Test
    public void number_of_booking_during_time_period_returns_1_if_queried_with_end_date_in_the_middle_of_an_existing_booking() {
        LocalDate booking_from = LocalDate.of(2015, 7, 1);

        LocalDate booking_to = LocalDate.of(2015, 7, 18);

        LocalDate query_from = LocalDate.of(2015, 6, 12);
        LocalDate query_to = LocalDate.of(2015, 7, 12);


        Date from_date = Date.from(booking_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date to_date = Date.from(booking_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Booking booking1 = Booking.builder().apartment(apartment).customer(customer)
                .startDate(from_date).endDate(to_date).build();

        repository.save(booking1);

        Date query_from_date = Date.from(query_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date query_to_date = Date.from(query_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        assertThat(repository.findNumberOfBookingDuringTimePeriod(apartment, query_from_date, query_to_date), is(1));

    }

    @Test
    public void number_of_booking_during_time_period_returns_1_if_queried_with_start_date_in_the_middle_of_an_existing_booking() {
        LocalDate booking_from = LocalDate.of(2015, 7, 1);

        LocalDate booking_to = LocalDate.of(2015, 7, 18);

        LocalDate query_from = LocalDate.of(2015, 7, 12);
        LocalDate query_to = LocalDate.of(2015, 8, 12);


        Date from_date = Date.from(booking_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date to_date = Date.from(booking_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Booking booking1 = Booking.builder().apartment(apartment).customer(customer)
                .startDate(from_date).endDate(to_date).build();

        repository.save(booking1);

        Date query_from_date = Date.from(query_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date query_to_date = Date.from(query_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        assertThat(repository.findNumberOfBookingDuringTimePeriod(apartment, query_from_date, query_to_date), is(1));

    }


    @Test
    public void number_of_booking_during_time_period_returns_1_if_queried_with_start_date_and_end_date_in_the_middle_of_an_existing_booking() {
        LocalDate booking_from = LocalDate.of(2015, 7, 1);

        LocalDate booking_to = LocalDate.of(2015, 7, 18);

        LocalDate query_from = LocalDate.of(2015, 7, 12);
        LocalDate query_to = LocalDate.of(2015, 7, 13);


        Date from_date = Date.from(booking_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date to_date = Date.from(booking_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Booking booking1 = Booking.builder().apartment(apartment).customer(customer)
                .startDate(from_date).endDate(to_date).build();

        repository.save(booking1);

        Date query_from_date = Date.from(query_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date query_to_date = Date.from(query_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        assertThat(repository.findNumberOfBookingDuringTimePeriod(apartment, query_from_date, query_to_date), is(1));

    }



    @Test
    public void number_of_booking_during_time_period_returns_1_if_queried_with_start_date_before_beginning_of_existing_booking_and_query_end_after_booking_end() {
        LocalDate booking_from = LocalDate.of(2015, 7, 1);

        LocalDate booking_to = LocalDate.of(2015, 7, 18);

        LocalDate query_from = LocalDate.of(2014, 7, 12);
        LocalDate query_to = LocalDate.of(2016, 7, 13);


        Date from_date = Date.from(booking_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date to_date = Date.from(booking_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Booking booking1 = Booking.builder().apartment(apartment).customer(customer)
                .startDate(from_date).endDate(to_date).build();

        repository.save(booking1);

        Date query_from_date = Date.from(query_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date query_to_date = Date.from(query_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        assertThat(repository.findNumberOfBookingDuringTimePeriod(apartment, query_from_date, query_to_date), is(1));

    }


    @Test
    public void number_of_booking_during_time_period_returns_0_if_queried_with_start_date_equals_to_end_date_of_booking() {
        LocalDate booking_from = LocalDate.of(2015, 7, 1);

        LocalDate booking_to = LocalDate.of(2015, 7, 18);

        LocalDate query_from = booking_to;
        LocalDate query_to = LocalDate.of(2016, 7, 13);


        Date from_date = Date.from(booking_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date to_date = Date.from(booking_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Booking booking1 = Booking.builder().apartment(apartment).customer(customer)
                .startDate(from_date).endDate(to_date).build();

        repository.save(booking1);

        Date query_from_date = Date.from(query_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date query_to_date = Date.from(query_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        assertThat(repository.findNumberOfBookingDuringTimePeriod(apartment, query_from_date, query_to_date), is(0));

    }


    @Test
    public void number_of_booking_during_time_period_returns_0_if_queried_with_end_date_equals_to_start_date_of_booking() {
        LocalDate booking_from = LocalDate.of(2015, 7, 1);

        LocalDate booking_to = LocalDate.of(2015, 7, 18);

        LocalDate query_from = LocalDate.of(2014, 7, 1);
        LocalDate query_to = booking_from;


        Date from_date = Date.from(booking_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date to_date = Date.from(booking_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Booking booking1 = Booking.builder().apartment(apartment).customer(customer)
                .startDate(from_date).endDate(to_date).build();

        repository.save(booking1);

        Date query_from_date = Date.from(query_from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date query_to_date = Date.from(query_to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        assertThat(repository.findNumberOfBookingDuringTimePeriod(apartment, query_from_date, query_to_date), is(0));

    }


}
