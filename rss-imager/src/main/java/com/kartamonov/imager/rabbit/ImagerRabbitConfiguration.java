package com.kartamonov.imager.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kartamonov.data.constants.RabbitConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class ImagerRabbitConfiguration {
    @Bean
    public DirectExchange itemDirectExchange() {
        return new DirectExchange(RabbitConstants.EXCHANGE_NAME);
    }

    @Bean
    public Queue saveItemsQueue() {
        return new Queue(RabbitConstants.QueuesNames.SAVE_ITEMS);
    }

    @Bean
    public Queue getAllItemsQueue() {
        return new Queue(RabbitConstants.QueuesNames.GET_ALL);
    }

    @Bean
    public Queue getItemByIdQueue() {
        return new Queue(RabbitConstants.QueuesNames.GET_ITEM_BY_ID);
    }

    @Bean
    public Queue getItemsByAuthorQueue() {
        return new Queue(RabbitConstants.QueuesNames.GET_ITEMS_BY_AUTHOR);
    }

    @Bean
    public Binding saveItemsBinding(DirectExchange itemDirectExchange, Queue saveItemsQueue) {
        return BindingBuilder.bind(saveItemsQueue).to(itemDirectExchange)
                .withQueueName();
    }

    @Bean
    public Binding getAllItemsBinding(DirectExchange itemDirectExchange, Queue getAllItemsQueue) {
        return BindingBuilder.bind(getAllItemsQueue).to(itemDirectExchange)
                .withQueueName();
    }

    @Bean
    public Binding getItemByIdBinding(DirectExchange itemDirectExchange, Queue getItemByIdQueue) {
        return BindingBuilder.bind(getItemByIdQueue).to(itemDirectExchange)
                .withQueueName();
    }

    @Bean
    public Binding getItemsByAuthorBinding(DirectExchange itemDirectExchange, Queue getItemsByAuthorQueue) {
        return BindingBuilder.bind(getItemsByAuthorQueue).to(itemDirectExchange)
                .withQueueName();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper());
    }

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }
}
