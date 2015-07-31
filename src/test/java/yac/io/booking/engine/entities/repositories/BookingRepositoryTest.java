package yac.io.booking.engine.entities.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import yac.io.booking.engine.entities.Apartment;
import yac.io.booking.engine.entities.Booking;
import yac.io.booking.engine.entities.Customer;

import static org.junit.Assert.assertEquals;

/**
 * Created by geoffroy on 31/07/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = RepositoryConfig.class)
public class BookingRepositoryTest {
    @Autowired ApartmentRepository apartmentRepository;
    @Autowired CustomerRepository customerRepository;
    @Autowired BookingRepository repository;

    @Test
    public void testInsert() {

        Apartment apartment = apartmentRepository.save(Apartment.builder().capacity(3).name("name").build());
        Customer customer = customerRepository.save(Customer.builder().firstName("firstName").lastName("lastName").build());


        Booking b = new Booking();
        b.setApartment(apartment);
        b.setCustomer(customer);

        b = repository.save(b);

        assertEquals(b, repository.findOne(b.getId()));
    }
}
