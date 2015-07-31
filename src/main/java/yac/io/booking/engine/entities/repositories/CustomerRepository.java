package yac.io.booking.engine.entities.repositories;

import org.springframework.data.repository.CrudRepository;
import yac.io.booking.engine.entities.Customer;

/**
 * Created by geoffroy on 31/07/15.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long>{
}
