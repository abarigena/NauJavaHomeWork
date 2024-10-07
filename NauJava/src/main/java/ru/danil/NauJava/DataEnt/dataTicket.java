package ru.danil.NauJava.DataEnt;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.danil.NauJava.ent.Ticket;


import java.util.ArrayList;
import java.util.List;

@Component
public class dataTicket
{
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Ticket> ticketsContainer(){
        return new ArrayList<>();
    }

}
