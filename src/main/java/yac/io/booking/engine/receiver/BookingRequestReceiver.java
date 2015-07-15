package yac.io.booking.engine.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Created by geoffroy on 15/07/15.
 */
@Component
public class BookingRequestReceiver {

    @RabbitListener(queues = "yac-booking-request")
    public void processBooking(byte[] bytes) {
        //TODO implement
        String message = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(message);
    }
}
