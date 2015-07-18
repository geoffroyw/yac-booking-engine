package yac.io.booking.engine.creation.manager;

import yac.io.booking.request.BookingRequest;

import java.text.ParseException;

/**
 * Created by geoffroy on 18/07/15.
 */
public interface BookingCreationManager {

    void processRequest(BookingRequest request) throws ParseException;
}
