package yac.io.booking.engine;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import yac.io.booking.engine.receiver.BookingRequestReceiver;

/**
 * Created by geoffroy on 15/07/15.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    final static String QUEUE_NAME = "yac-booking-request";

    @Autowired
    AnnotationConfigApplicationContext context;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    BookingRequestReceiver bookingRequestReceiver() {
        return new BookingRequestReceiver();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(BookingRequestReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        rabbitTemplate.receive(QUEUE_NAME);
        context.close();
    }


}
