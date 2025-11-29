package com.fitness.activityservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue activityQueue(){
        return new Queue("activity.queue",true);
    }
    // 2. Exchange (Προσθήκη)
    @Bean
    public TopicExchange activityExchange() {
        // Χρησιμοποιούμε TopicExchange, καθώς το routing key υποδηλώνει αυτό το pattern.
        return new TopicExchange("fitness.exchange");
    }

    // 3. Binding (Σύνδεση Exchange με Queue μέσω Routing Key)
    @Bean
    public Binding binding(Queue activityQueue, TopicExchange activityExchange) {
        // Χρησιμοποιούμε το routing key από το application.yml
        return BindingBuilder
                .bind(activityQueue)
                .to(activityExchange)
                .with("activity.tracking");
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
