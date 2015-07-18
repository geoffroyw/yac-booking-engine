package yac.io.booking.engine.creation.manager.impl;

import yac.io.booking.engine.creation.manager.BookingCreationManager;
import yac.io.booking.engine.entities.Booking;
import yac.io.booking.request.BookingRequest;

import java.text.ParseException;

/**
 * Created by geoffroy on 18/07/15.
 */
public class BookingCreationManagerImpl implements BookingCreationManager {

    @Override
    public void processRequest(BookingRequest request) throws ParseException {
        Booking booking = request.toBooking();
    }

}
