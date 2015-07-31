package yac.io.booking.engine.entities.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import yac.io.booking.engine.entities.Apartment;
import yac.io.booking.engine.entities.Booking;

import java.util.Date;

/**
 * Created by geoffroy on 31/07/15.
 */
public interface BookingRepository extends CrudRepository<Booking, Long> {

    //(start_date <= :start_date AND end_date > :start_date) OR (start_date <= :end_date AND end_date > :end_date)
    @Query("select count(b) from Booking b where b.apartment = :apartment and ((b.startDate <= :from and b.endDate > :from) OR (b.startDate <= :to and b.endDate > :to))")
    Integer findNumberOfBookingDuringTimePeriod(@Param("apartment")Apartment apartment, @Param("from")Date from, @Param("to") Date to);
}
