package ru.danil.NauJava.service.reportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.danil.NauJava.Entities.Report.Report;
import ru.danil.NauJava.Entities.Report.ReportStatus;
import ru.danil.NauJava.Entities.Ticket.Ticket;
import ru.danil.NauJava.Entities.User.UserRole;
import ru.danil.NauJava.Repository.reportRepository.ReportRepository;
import ru.danil.NauJava.Repository.ticketRepository.TicketRepository;
import ru.danil.NauJava.Repository.userRepository.UserRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Сервис для управления объектами сущности {@link Report}.
 */
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, TicketRepository ticketRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    /**
     * Создает новый отчет и сохраняет его в базе данных.
     *
     * @return id созданного отчета
     */
    @Override
    public Long createReport() {
        Report report = new Report();
        report.setStatus(ReportStatus.CREATED);
        reportRepository.save(report);
        return report.getId();
    }

    /**
     * Получает содержимое отчета по его идентификатору.
     *
     * @param id идентификатор отчета
     * @return содержимое отчета, или null, если отчет не найден
     */
    @Override
    public String getReportContent(Long id) {
        return reportRepository.findById(id)
                .map(Report::getContent)
                .orElse(null);
    }

    /**
     * Получает информацию о статусе отчета по его идентификатору
     *
     * @param id идентификатор отчета
     * @return статус отчета, или null, если не найден
     */
    @Override
    public ReportStatus getReportStatus(Long id) {
        return reportRepository.findById(id)
                .map(Report::getStatus)
                .orElse(null);
    }

    /**
     * Генерирует отчет на основе заданного идентификатора отчета.
     * <p>
     * Асинхронно подсчитывает количество пользователей с ролью USER и получает
     * список всех билетов. Время выполнения каждой операции измеряется, а затем
     * формируется HTML-отчет с полученными данными.
     *
     * @param reportId идентификатор отчета, который необходимо сгенерировать
     * @return {@code CompletableFuture<Void>} объект, указывающий на завершение операции
     * @throws RuntimeException если отчет с указанным идентификатором не найден
     */
    @Override
    public CompletableFuture<Void> generateReport(Long reportId) {
        //Запоминаем время начала
        long startTime = System.currentTimeMillis();
        final long[] userCountTime = new long[1];
        final long[] ticketsFetchTime = new long[1];

        try {
            Report report = reportRepository.findById(reportId)
                    .orElseThrow(() -> new RuntimeException("Report not found"));

            // Создаем поток для вычисления количества пользователей
            CompletableFuture<Long> userCountFuture = CompletableFuture.supplyAsync(() -> {
                long startUserCount = System.currentTimeMillis();
                final long[] userCount = new long[1];
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(2000); //симуляция длительности
                        userCount[0] = userRepository.countByUserRoles(UserRole.USER);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                userCountTime[0] = System.currentTimeMillis() - startUserCount;
                return userCount[0];
            });

            //Создаем поток для получения списка билетов
            CompletableFuture<List<Ticket>> ticketFuture = CompletableFuture.supplyAsync(() -> {
                long startTickets = System.currentTimeMillis();
                final List<Ticket>[] ticketsList = new List[]{null};

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        ticketsList[0] = (List<Ticket>) ticketRepository.findAll();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                thread.start();

                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                ticketsFetchTime[0] = System.currentTimeMillis() - startTickets;
                return ticketsList[0];
            });

            Long userCount = userCountFuture.join();
            List<Ticket> tickets = ticketFuture.join();

            long elapsedTime = System.currentTimeMillis() - startTime;

            String reportContent = generateHtmlReport(userCount, tickets, userCountTime[0], ticketsFetchTime[0], elapsedTime);

            report.setStatus(ReportStatus.COMPLETED);
            report.setContent(reportContent);
            reportRepository.save(report);

        } catch (Exception e) {
            Report report = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
            report.setStatus(ReportStatus.ERROR);
            reportRepository.save(report);
        }


        //Считаем сколько времени прошло
        long endTime = (System.currentTimeMillis() - startTime);
        System.out.println("Затраченное время: " + endTime);

        return CompletableFuture.completedFuture(null);
    }

    private String generateHtmlReport(Long userCount, List<Ticket> tickets,
                                      Long userCountTime, Long ticketsFetchTime, Long totalElapsedTime) {
        StringBuilder htmlReport = new StringBuilder();

        htmlReport.append("<html>")
                .append("<head><title>Отчет</title></head>")
                .append("<body>")
                .append("<h1>Отчет по состоянию системы</h1>")
                .append("<h2>Количество пользователей</h2>")
                .append("<p>Зарегистрированные пользователи: ").append(userCount).append("</p>")
                .append("<p>Время затраченное на подсчет пользователей: ").append(userCountTime).append(" мс</p>")
                .append("<h2>Список билетов</h2>")
                .append("<table border='1'>")
                .append("<tr><th>ID</th><th>Владелец билета</th><th>Цена</th></tr>");

        for (Ticket ticket : tickets) {
            htmlReport.append("<tr>")
                    .append("<td>").append(ticket.getId()).append("</td>")
                    .append("<td>").append(ticket.getUser().getUsername()).append("</td>")
                    .append("<td>").append(ticket.getPrice()).append("<td>")
                    .append("<tr>");
        }

        htmlReport.append("</table>")
                .append("<p>Время затраченное на получение списка билетов: ").append(ticketsFetchTime).append(" мс</p>")
                .append("<h2>Общее время формирования отчета</h2>")
                .append("<p>").append(totalElapsedTime).append(" мс</p>")
                .append("</body>")
                .append("</html>");

        return htmlReport.toString();
    }

}
