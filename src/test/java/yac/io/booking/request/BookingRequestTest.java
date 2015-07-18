package yac.io.booking.request;

import org.junit.Test;
import yac.io.booking.engine.entities.Booking;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by geoffroy on 18/07/15.
 */
public class BookingRequestTest {

    private BookingRequest.Builder prototypeBookingRequest() {
        return BookingRequest.builder().start_date("2012-12-31").end_date("2013-01-01");
    }


    @Test
    public void request_start_date_maps_to_booking_start_date() throws ParseException {
        BookingRequest request = prototypeBookingRequest().start_date("2015-12-31").build();
        LocalDate startDate = LocalDate.of(2015, Month.DECEMBER, 31);

        Booking booking = request.toBooking();

        assertThat(booking.getStartDate(), is(Date.from(startDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
    }

    @Test
    public void request_end_date_maps_to_booking_end_date() throws ParseException {
        BookingRequest request = prototypeBookingRequest().end_date("2017-06-12").build();
        LocalDate endDate = LocalDate.of(2017, Month.JUNE, 12);

        Booking booking = request.toBooking();

        assertThat(booking.getEndDate(), is(Date.from(endDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
    }

    @Test
    public void request_number_of_adult_maps_to_booking_numberOfAdult() throws ParseException {
        BookingRequest request = prototypeBookingRequest().number_of_adult(3).build();

        Booking booking = request.toBooking();

        assertThat(booking.getNumberOfAdult(), is(3));
    }

    @Test
    public void request_number_of_children_maps_to_booking_numberOfChildren() throws ParseException {
        BookingRequest request = prototypeBookingRequest().number_of_children(2).build();

        Booking booking = request.toBooking();

        assertThat(booking.getNumberOfChildren(), is(2));
    }
}
