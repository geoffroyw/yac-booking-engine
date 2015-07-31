package yac.io.booking.request;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;


/**
 * Created by geoffroy on 15/07/15.
 */
public class BookingRequestReceiver implements MessageListener {

    private static final Log LOGGER = LogFactory.getLog(BookingRequestReceiver.class);

    @Bean
    private ClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setDefaultType(BookingRequest.class);
        return classMapper;
    }

    public void onMessage(Message m) {
        LOGGER.info("Received booking request " + new String(m.getBody(), StandardCharsets.UTF_8));

        m.getMessageProperties().setContentType("text/x-json");
        Jackson2JsonMessageConverter jackson2JavaTypeMapper = new Jackson2JsonMessageConverter();
        jackson2JavaTypeMapper.setClassMapper(classMapper());
        BookingRequest r = (BookingRequest) jackson2JavaTypeMapper.fromMessage(m);

        System.out.println(r);
    }

}
