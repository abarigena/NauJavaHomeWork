package ru.danil.NauJava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.danil.NauJava.ent.Ticket;
import ru.danil.NauJava.service.Inteface.TicketService;

@Component
public class CommandProcessor
{
    private final TicketService ticketService;

    @Autowired
    public CommandProcessor(TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

    public void processCommand(String input)
    {
        String[] commands = input.split(" ");
        switch (commands[0])
        {
            case "Create" ->
            {
                ticketService.createTicket(Long.valueOf(commands[1]),commands[2], commands[3], commands[4] );
                System.out.println("Билет успешно создан");
            }
            case "Delete" -> {
                Ticket ticket = ticketService.findById(Long.valueOf(commands[1]));
                if(ticket != null){
                    ticketService.deleteById(Long.valueOf(commands[1]));
                    System.out.println("Билет - "+ticket+" был успешно удален");
                }else {
                    System.out.println("Билет не найден");
                }
            }

            case "Find" ->
            {
                Ticket ticket = ticketService.findById(Long.valueOf(commands[1]));
                if(ticket != null){
                    System.out.println(ticket);
                }else {
                    System.out.println("Билет не найден");
                }
            }

            case "Update" ->
            {
                if (commands.length<5){
                    System.out.println("Недостаточно информации для обновления билета");
                    return;
                }
                Ticket ticketOld = ticketService.findById(Long.valueOf(commands[1]));
                if(ticketOld != null){
                    ticketService.updateById(Long.valueOf(commands[1]),commands[2], commands[3], commands[4] );
                    Ticket tickeNew = ticketService.findById(Long.valueOf(commands[1]));
                    System.out.println("Билет - " + ticketOld);
                    System.out.println("Был обновлен - "+ tickeNew);
                }else {
                    System.out.println("Билет не найден");
                }
            }

            default -> System.out.println("Введена неизвестная команда");
        }

    }
}
