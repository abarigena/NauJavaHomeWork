package ru.danil.NauJava.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.danil.NauJava.Entities.Ticket.Ticket;
import ru.danil.NauJava.Repository.ticketRepository.TicketRepository;

/**
 * Контроллер для управления представлением билетов.
 * <p>
 * Этот контроллер обрабатывает запросы, связанные с отображением билетов.
 * Он использует {@link TicketRepository} для получения данных о билетах
 * и возвращает соответствующее представление.
 */
@Controller
@RequestMapping("/custom/tickets/view")
public class TicketControllerView {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketControllerView(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    /**
     * Обрабатывает GET-запрос на получение списка билетов.
     * <p>
     * Метод извлекает все доступные билеты из репозитория,
     * добавляет их в модель и возвращает имя представления для отображения.
     *
     * @param model модель, используемая для передачи данных в представление
     * @return имя представления, которое отображает список билетов
     */
    @GetMapping("/list")
    public String listTicketsView(Model model) {
        Iterable<Ticket> products = ticketRepository.findAll();
        model.addAttribute("tickets", products);
        return "ticketList";
    }
}
