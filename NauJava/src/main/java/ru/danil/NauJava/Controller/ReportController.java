package ru.danil.NauJava.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.danil.NauJava.Entities.Report.ReportStatus;
import ru.danil.NauJava.service.reportService.ReportService;

/**
 * Контроллер для управления операциями с отчетами.
 */
@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Создает новый отчет и инициирует его асинхронную генерацию.
     *
     * @return {@link ResponseEntity} с идентификатором созданного отчета
     */
    @GetMapping("/create")
    public ResponseEntity<Long> createReport() {
        Long reportId = reportService.createReport();

        reportService.generateReport(reportId);

        return ResponseEntity.ok(reportId);
    }

    /**
     * Получает содержимое отчета по его идентификатору.
     * Если отчет все еще формируется, возвращается сообщение об ожидании.
     * Если произошла ошибка, возвращается сообщение об ошибке.
     *
     * @param reportId идентификатор отчета
     * @return {@link ResponseEntity} с содержимым отчета или соответствующим сообщением
     */
    @GetMapping("/{reportId}")
    public ResponseEntity<String> getReportContentController(@PathVariable Long reportId) {
        String reportContent = reportService.getReportContent(reportId);

        if(reportContent != null && !reportContent.isEmpty()) {
            return ResponseEntity.ok(reportContent);
        }

        ReportStatus reportStatus = reportService.getReportStatus(reportId);

        if(reportStatus == ReportStatus.CREATED) {
            return ResponseEntity.ok("Отчет еще формируется. Пожалуйста, подождите.");
        } else if (reportStatus == ReportStatus.ERROR) {
            return ResponseEntity.ok("Произошла ошибка при формировании отчета.");
        }

        return ResponseEntity.notFound().build();
    }
}
