package ru.danil.NauJava.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.danil.NauJava.Entities.Report.Report;
import ru.danil.NauJava.Entities.Report.ReportStatus;
import ru.danil.NauJava.Entities.Ticket.Ticket;
import ru.danil.NauJava.Entities.User.User;
import ru.danil.NauJava.Repository.reportRepository.ReportRepository;
import ru.danil.NauJava.Repository.ticketRepository.TicketRepository;
import ru.danil.NauJava.Repository.userRepository.UserRepository;
import ru.danil.NauJava.service.reportService.ReportService;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
class ReportTest {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final ReportService reportService;
    private final ReportRepository reportRepository;

    @Autowired
    public ReportTest(UserRepository userRepository, TicketRepository ticketRepository, ReportService reportService, ReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.reportService = reportService;
        this.reportRepository = reportRepository;
    }
    @Test
    void testGenerateReport() {
        Long reportId = reportService.createReport();

        CompletableFuture<Void> future = reportService.generateReport(reportId);

        future.join();

        Report generatedReport = reportRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));

        Assertions.assertEquals(ReportStatus.COMPLETED, generatedReport.getStatus());

        String reportContent = reportService.getReportContent(reportId);
        Assertions.assertNotNull(reportContent);
        System.out.println("Content: " + reportContent);

    }

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 20; i++) {
            User user = new User();
            user.setFirstName(UUID.randomUUID().toString());
            userRepository.save(user);
        }
        for (int i = 0; i < 20; i++) {
            Ticket ticket = new Ticket();
            double ticketPrice = Math.random() * 1000;
            ticket.setPrice(ticketPrice);
            ticketRepository.save(ticket);
        }
    }
}
