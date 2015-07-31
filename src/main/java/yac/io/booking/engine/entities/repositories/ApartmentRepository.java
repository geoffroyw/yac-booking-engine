package yac.io.booking.engine.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import yac.io.booking.engine.entities.Apartment;

/**
 * Created by geoffroy on 31/07/15.
 */
public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
}
