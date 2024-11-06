package ru.danil.NauJava.service.reportService;

import ru.danil.NauJava.Entities.Report.ReportStatus;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс для сервиса отчетов.
 */
public interface ReportService {

    /**
     * Создает новый отчет.
     *
     * @return уникальный идентификатор созданного отчета.
     */
    Long createReport();

    /**
     * Получает содержимое отчета по идентификатору.
     *
     * @param id идентификатор отчета.
     * @return содержимое отчета в виде строки.
     */
    String getReportContent(Long id);

    /**
     * Получает статус отчета по идентификатору.
     *
     * @param id идентификатор отчета.
     * @return статус отчета.
     */
    ReportStatus getReportStatus(Long id);

    /**
     * Асинхронно генерирует отчет.
     *
     * @param reportId идентификатор отчета для генерации.
     */
    void generateReport(Long reportId);
}
