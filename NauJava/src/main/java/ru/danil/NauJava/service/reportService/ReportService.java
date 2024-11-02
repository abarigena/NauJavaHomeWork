package ru.danil.NauJava.service.reportService;

import ru.danil.NauJava.Entities.Report.Report;
import ru.danil.NauJava.Entities.Report.ReportStatus;

import java.util.concurrent.CompletableFuture;

public interface ReportService {

    Long createReport();

    String getReportContent(Long id);

    ReportStatus getReportStatus(Long id);

    CompletableFuture<Void> generateReport(Long reportId);
}
