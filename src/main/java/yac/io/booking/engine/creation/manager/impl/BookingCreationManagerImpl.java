package yac.io.booking.engine.creation.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import yac.io.booking.engine.creation.exception.BookingCreationException;
import yac.io.booking.engine.creation.manager.BookingCreationManager;
import yac.io.booking.engine.entities.Booking;
import yac.io.booking.engine.entities.repositories.BookingRepository;
import yac.io.booking.request.BookingRequest;

import java.text.ParseException;

/**
 * Created by geoffroy on 18/07/15.
 */
public class BookingCreationManagerImpl implements BookingCreationManager {

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public void processRequest(BookingRequest request) throws ParseException, BookingCreationException {
        Booking booking = request.toBooking();

        this.makeBooking(booking);

    }

    private synchronized Booking makeBooking(Booking booking) throws BookingCreationException {

        if(bookingRepository.findNumberOfBookingDuringTimePeriod(booking.getApartment(), booking.getStartDate(), booking.getEndDate()) != 0) {
            System.out.println("Apartment is alreadw booked");
            throw new BookingCreationException("Apartment is already booked");
        }

        return bookingRepository.save(booking);
    }

}
